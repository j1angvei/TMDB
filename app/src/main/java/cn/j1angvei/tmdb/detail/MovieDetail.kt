package cn.j1angvei.tmdb.detail

import cn.j1angvei.tmdb.TMDB_IMG_URL
import java.util.Date

/**
 *
 * @author : jiangwei.man
 * @since : 2023/8/7
 **/
data class MovieDetail(
    val id: Int,
    val budget: Long,
    val genres: List<Genre>,
    val title: String,
    val originalTitle: String,
    val posterPath: String,
    val releaseDate: Date,
    val voteAverage: Float,
    val voteCount: Int,
    val revenue: Long,
    val runtime: Int,
    val overview: String,
    val credits: Credits
) {
    val fullPoster: String
        get() = TMDB_IMG_URL + posterPath
}