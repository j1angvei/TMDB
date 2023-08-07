package cn.j1angvei.tmdb.list.person

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.ExperimentalPagingApi
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.cachedIn
import cn.j1angvei.tmdb.TMDB_PAGE_SIZE
import cn.j1angvei.tmdb.db.TmdbDataBase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class PersonListViewModel @Inject constructor(
    private val db: TmdbDataBase, private val remoteMediator: PersonRemoteMediator
) : ViewModel() {

    @OptIn(ExperimentalPagingApi::class)
    val movieFlow = Pager(PagingConfig(TMDB_PAGE_SIZE),
        remoteMediator = remoteMediator,
        pagingSourceFactory = { db.personDao().pagingSource() }).flow.cachedIn(viewModelScope)
}