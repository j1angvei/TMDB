package cn.j1angvei.tmdb.detail

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import cn.j1angvei.tmdb.EXTRA_MOVIE_ID
import cn.j1angvei.tmdb.EXTRA_MOVIE_TITLE
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
 * @since : 2023/8/7
 **/
@HiltViewModel
class MovieDetailViewModel @Inject constructor(
    private val stateHandle: SavedStateHandle,
    private val apiService: TmdbApiService
) : ViewModel() {

    private val movieId = stateHandle[EXTRA_MOVIE_ID] ?: 0
    val movieTitle = stateHandle[EXTRA_MOVIE_TITLE] ?: "电影详情"

    private val _movieDetail = MutableLiveData<MovieDetail?>()
    val movieDetail: LiveData<MovieDetail?>
        get() = _movieDetail

    fun fetchMovieDetail() {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                _movieDetail.postValue(apiService.movieDetail(movieId))
            } catch (e: IOException) {
                _movieDetail.postValue(null)
                Log.e(TAG, "fetchMovieDetail: ", e)
            } catch (e: HttpException) {
                _movieDetail.postValue(null)
                Log.e(TAG, "fetchMovieDetail: ", e)
            }
        }
    }
}