package cn.j1angvei.tmdb

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.Date

@Entity("movie")
data class Movie(
    @PrimaryKey val id: Int,
    val backdropPath: String?,
    val genreIds: List<Int>,
    val title: String,
    val originalTitle: String,
    val overview: String,
    val releaseDate: Date,
    val posterPath: String,
    val voteAverage: Float,
    val voteCount: Int,
    var page: Int,
    var idxInPage: Int
) {

    val fullPoster: String
        get() = TMDB_IMG_URL + posterPath
}