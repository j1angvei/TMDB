package cn.j1angvei.tmdb

import java.util.Date

data class Movie(
    val id: Int,
    val backdropPath: String,
    val genreIds: List<Int>,
    val title: String,
    val originalTitle: String,
    val overview: String,
    val releaseDate: Date,
    val posterPath: String,
    val voteAverage: Float,
    val voteCount: Int
) {

    val fullPoster: String
        get() = TMDB_IMG_URL + posterPath
}