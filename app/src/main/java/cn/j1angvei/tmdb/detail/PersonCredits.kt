package cn.j1angvei.tmdb.detail

import cn.j1angvei.tmdb.TMDB_IMG_URL
import com.google.gson.annotations.SerializedName

data class PersonCredits(val cast: List<Cast>) {

    data class Cast(
        val id: Int,
        @SerializedName("name", alternate = ["title"])
        val title: String?,
        val posterPath: String,
        val voteAverage: Float,
        val character: String,
    ) {
        val fullPoster: String
            get() = TMDB_IMG_URL + posterPath
    }
}
