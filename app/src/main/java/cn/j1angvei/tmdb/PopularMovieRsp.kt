package cn.j1angvei.tmdb


data class PopularMovieRsp(
    val page: Int,
    val results: List<Movie>,
    val totalPages: Int,
) {
    fun remoteKey(): RemoteKey {
        return RemoteKey(page, totalPages)
    }
}