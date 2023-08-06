package cn.j1angvei.tmdb

import com.squareup.moshi.Json

data class PopularMovieRsp(
    val page: Int,
    val results: List<Movie>,
    @Json(name = "total_pages") val totalPages: Int,
) {
    fun remoteKey(): RemoteKey {
        return RemoteKey(page, totalPages)
    }
}