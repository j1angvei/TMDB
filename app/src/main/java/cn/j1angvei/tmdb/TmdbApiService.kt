package cn.j1angvei.tmdb

import retrofit2.http.GET
import retrofit2.http.Query

interface TmdbApiService {

    @GET("movie/popular")
    suspend fun popularMovies(
        @Query("page") page: Int, @Query("language") language: String = "zh-CN"
    ): PopularMovieRsp

}