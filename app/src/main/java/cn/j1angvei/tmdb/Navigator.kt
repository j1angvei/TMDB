package cn.j1angvei.tmdb

import android.content.Context
import android.content.Intent
import cn.j1angvei.tmdb.detail.MovieDetailActivity
import cn.j1angvei.tmdb.detail.PersonDetailActivity

/**
 *
 * @author : jiangwei.man
 * @since : 2023/8/8
 **/
object Navigator {
    fun toMovieDetail(context: Context, movieId: Int, movieTitle: String) {
        val intent = Intent(context, MovieDetailActivity::class.java).apply {
            putExtra(EXTRA_MOVIE_ID, movieId)
            putExtra(EXTRA_MOVIE_TITLE, movieTitle)
        }
        context.startActivity(intent)
    }

    fun toPersonDetail(context: Context, personId: Int, personName: String) {
        val intent = Intent(context, PersonDetailActivity::class.java).apply {
            putExtra(EXTRA_PERSON_ID, personId)
            putExtra(EXTRA_PERSON_NAME, personName)
        }
        context.startActivity(intent)
    }
}