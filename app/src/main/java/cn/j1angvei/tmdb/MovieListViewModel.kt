package cn.j1angvei.tmdb

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.cachedIn
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MovieListViewModel @Inject constructor(private val source: MoviePagingSource) : ViewModel() {

    val movieFlow = Pager(PagingConfig(TMDB_PAGE_SIZE)) { source }.flow.cachedIn(viewModelScope)
}