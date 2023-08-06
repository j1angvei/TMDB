package cn.j1angvei.tmdb

import androidx.paging.PagingSource
import androidx.paging.PagingState
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class MoviePagingSource @Inject constructor(private val apiService: TmdbApiService) :
    PagingSource<Int, Movie>() {
    override fun getRefreshKey(state: PagingState<Int, Movie>): Int? {
        return null
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Movie> {
        return try {
            val curPage = params.key ?: 1
            val prevPage = if (curPage > 1) curPage - 1 else null
            val rsp = apiService.popularMovies(curPage)
            val nextPage = if (rsp.page == rsp.totalPages) null else rsp.page + 1
            return LoadResult.Page(rsp.results, prevPage, nextPage)
        } catch (e: IOException) {
            LoadResult.Error(e)
        } catch (e: HttpException) {
            LoadResult.Error(e)
        }

    }
}