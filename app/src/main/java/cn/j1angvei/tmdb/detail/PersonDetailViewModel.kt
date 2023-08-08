package cn.j1angvei.tmdb.detail

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import cn.j1angvei.tmdb.EXTRA_PERSON_ID
import cn.j1angvei.tmdb.EXTRA_PERSON_NAME
import cn.j1angvei.tmdb.TAG
import cn.j1angvei.tmdb.api.TmdbApiService
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

/**
 *
 * @author : jiangwei.man
 * @since : 2023/8/8
 **/
@HiltViewModel
class PersonDetailViewModel @Inject constructor(
    private val stateHandle: SavedStateHandle,
    private val apiService: TmdbApiService
) : ViewModel() {

    private val personId = stateHandle[EXTRA_PERSON_ID] ?: 0
    val personName = stateHandle[EXTRA_PERSON_NAME] ?: ""

    private val _personDetail = MutableLiveData<PersonDetail?>()
    val personDetail: LiveData<PersonDetail?>
        get() = _personDetail

    fun fetchPersonDetail() {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                _personDetail.postValue(apiService.personDetail(personId))
            } catch (e: IOException) {
                _personDetail.postValue(null)
                Log.e(TAG, "fetchPersonDetail: ", e)
            } catch (e: HttpException) {
                _personDetail.postValue(null)
                Log.e(TAG, "fetchPersonDetail: ", e)
            }
        }
    }
}