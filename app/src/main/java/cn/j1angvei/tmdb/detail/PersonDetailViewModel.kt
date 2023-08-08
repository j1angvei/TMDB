package cn.j1angvei.tmdb.detail

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import cn.j1angvei.tmdb.EXTRA_PERSON_ID
import cn.j1angvei.tmdb.EXTRA_PERSON_NAME
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

/**
 *
 * @author : jiangwei.man
 * @since : 2023/8/8
 **/
@HiltViewModel
class PersonDetailViewModel @Inject constructor(private val stateHandle: SavedStateHandle) :
    ViewModel() {

    private val personId = stateHandle[EXTRA_PERSON_ID] ?: 0
    val personName = stateHandle[EXTRA_PERSON_NAME] ?: ""
}