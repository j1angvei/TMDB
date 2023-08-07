package cn.j1angvei.tmdb.detail

data class PersonCredits<T>(val cast: List<T>) {
    data class TvCast(
        val id: Int,
        val name: String,
        val posterPath: String,
        val voteAverage: Float,
        val character: String,
        val episodeCount: Int
    )

    data class MovieCast(
        val id: Int,
        val name: String,
        val posterPath: String,
        val voteAverage: Float,
        val character: String,
    )
}
