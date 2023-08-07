package cn.j1angvei.tmdb.popular.person

import android.util.Log
import androidx.paging.ExperimentalPagingApi
import androidx.paging.LoadType
import androidx.paging.PagingState
import androidx.paging.RemoteMediator
import androidx.room.withTransaction
import cn.j1angvei.tmdb.DB_CACHE_EXPIRE_DURATION
import cn.j1angvei.tmdb.PageKey
import cn.j1angvei.tmdb.PageType
import cn.j1angvei.tmdb.TAG
import cn.j1angvei.tmdb.TMDB_START_PAGE
import cn.j1angvei.tmdb.TmdbApiService
import cn.j1angvei.tmdb.TmdbDataBase
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

@OptIn(ExperimentalPagingApi::class)
class PersonRemoteMediator @Inject constructor(
    private val db: TmdbDataBase, private val api: TmdbApiService
) : RemoteMediator<Int, Person>() {

    private val personDao = db.personDao()
    private val pageKeyDao = db.pageKeyDao()

    override suspend fun initialize(): InitializeAction {
        val lastUpdate = db.pageKeyDao().getCreateTime() ?: 0
        val initAction = if (System.currentTimeMillis() - lastUpdate > DB_CACHE_EXPIRE_DURATION) {
            InitializeAction.LAUNCH_INITIAL_REFRESH
        } else {
            InitializeAction.SKIP_INITIAL_REFRESH
        }
        Log.d(TAG, "initialize: $initAction")
        return initAction
    }

    override suspend fun load(loadType: LoadType, state: PagingState<Int, Person>): MediatorResult {
        val pageKey = when (loadType) {
            LoadType.REFRESH -> state.anchorPosition?.let { state.closestItemToPosition(it) }
            LoadType.PREPEND -> state.firstItemOrNull()
            LoadType.APPEND -> state.lastItemOrNull()
        }?.id?.let { movieId -> pageKeyDao.findByTypeAndId(movieId, PageType.PERSON) }
        val reqPage = when (loadType) {
            LoadType.REFRESH -> pageKey?.curPage ?: TMDB_START_PAGE
            LoadType.PREPEND -> pageKey?.prevPage
            LoadType.APPEND -> pageKey?.nextPage
        } ?: return MediatorResult.Success(endOfPaginationReached = pageKey != null)
        Log.d(TAG, "load: pageKey $pageKey, reqPage $reqPage")
        return try {
            val rsp = api.popularPeople(reqPage)
            db.withTransaction {
                if (loadType == LoadType.REFRESH) {
                    pageKeyDao.deleteAll()
                    personDao.deleteAll()
                }
                val pageKeys =
                    rsp.results.map { PageKey(PageType.PERSON, it.id, reqPage, rsp.totalPages) }
                pageKeyDao.insertAll(pageKeys)
                val movieList = rsp.results.onEachIndexed { index, movie ->
                    run {
                        movie.page = reqPage
                        movie.idxInPage = index
                    }
                }
                personDao.insertAll(movieList)
            }
            MediatorResult.Success(rsp.page == rsp.totalPages)
        } catch (e: IOException) {
            Log.e(TAG, "load: ", e)
            MediatorResult.Error(e)
        } catch (e: HttpException) {
            Log.e(TAG, "load: ", e)
            MediatorResult.Error(e)
        }
    }
}