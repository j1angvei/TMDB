package cn.j1angvei.tmdb

import androidx.paging.PagingSource
import androidx.paging.PagingState
import javax.inject.Inject

class MoviePagingSource constructor(@set:Inject var apiService: TmdbApiService) :
    PagingSource<RemoteKey, Movie>() {
    override fun getRefreshKey(state: PagingState<RemoteKey, Movie>): RemoteKey? {
        return null
    }

    override suspend fun load(params: LoadParams<RemoteKey>): LoadResult<RemoteKey, Movie> {
        TODO("Not yet implemented")
    }
}