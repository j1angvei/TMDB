package cn.j1angvei.tmdb.list.person

import androidx.paging.PagingSource
import androidx.paging.PagingState
import cn.j1angvei.tmdb.api.TmdbApiService
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class PersonPagingSource @Inject constructor(private val apiService: TmdbApiService) :
    PagingSource<Int, Person>() {
    override fun getRefreshKey(state: PagingState<Int, Person>): Int? {
        return null
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Person> {
        return try {
            val curPage = params.key ?: 1
            val prevPage = if (curPage > 1) curPage - 1 else null
            val rsp = apiService.popularPeople(curPage)
            val nextPage = if (rsp.page == rsp.totalPages) null else rsp.page + 1
            return LoadResult.Page(rsp.results, prevPage, nextPage)
        } catch (e: IOException) {
            LoadResult.Error(e)
        } catch (e: HttpException) {
            LoadResult.Error(e)
        }

    }
}