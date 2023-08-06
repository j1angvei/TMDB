package cn.j1angvei.tmdb

import android.util.Log
import androidx.paging.ExperimentalPagingApi
import androidx.paging.LoadType
import androidx.paging.PagingState
import androidx.paging.RemoteMediator
import androidx.room.withTransaction
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

@OptIn(ExperimentalPagingApi::class)
class MovieRemoteMediator @Inject constructor(
    private val db: TmdbDataBase, private val api: TmdbApiService
) : RemoteMediator<Int, Movie>() {

    private val movieDao = db.movieDao()
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

    override suspend fun load(loadType: LoadType, state: PagingState<Int, Movie>): MediatorResult {
        val pageKey = when (loadType) {
            LoadType.REFRESH -> state.anchorPosition?.let { state.closestItemToPosition(it) }
            LoadType.PREPEND -> state.firstItemOrNull()
            LoadType.APPEND -> state.lastItemOrNull()
        }?.id?.let { movieId -> pageKeyDao.findByTypeAndId(movieId, PageType.MOVIE) }
        val reqPage = when (loadType) {
            LoadType.REFRESH -> pageKey?.curPage ?: TMDB_START_PAGE
            LoadType.PREPEND -> pageKey?.prevPage
            LoadType.APPEND -> pageKey?.nextPage
        } ?: return MediatorResult.Success(endOfPaginationReached = pageKey != null)
        return try {
            val rsp = api.popularMovies(reqPage)
            db.withTransaction {
                if (loadType == LoadType.REFRESH) {
                    pageKeyDao.deleteAll()
                    movieDao.deleteAll()
                }
                val pageKeys =
                    rsp.results.map { PageKey(PageType.MOVIE, it.id, reqPage, rsp.totalPages) }
                pageKeyDao.insertAll(pageKeys)
                val movieList = rsp.results.onEachIndexed { index, movie ->
                    run {
                        movie.page = reqPage
                        movie.idxInPage = index
                    }
                }
                movieDao.insertAll(movieList)
            }
            MediatorResult.Success(rsp.page == rsp.totalPages)
        } catch (e: IOException) {
            MediatorResult.Error(e)
        } catch (e: HttpException) {
            MediatorResult.Error(e)
        }
    }
}