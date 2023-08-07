package cn.j1angvei.tmdb.api

import cn.j1angvei.tmdb.list.PopularRsp
import cn.j1angvei.tmdb.list.movie.Movie
import cn.j1angvei.tmdb.list.movie.MovieDetail
import cn.j1angvei.tmdb.list.person.Person
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface TmdbApiService {

    @GET("movie/popular")
    suspend fun popularMovies(
        @Query("page") page: Int, @Query("language") language: String = "zh-CN"
    ): PopularRsp<Movie>

    @GET("person/popular")
    suspend fun popularPeople(
        @Query("page") page: Int, @Query("language") language: String = "zh-CN"
    ): PopularRsp<Person>

    @GET("movie/{id}")
    suspend fun movieDetail(
        @Path("id") movieId: Int,
        @Query("language") language: String = "zh-CN",
        @Query("append_to_response") appendToRsp: String = "credits"
    ): MovieDetail
}