package cn.j1angvei.tmdb

import cn.j1angvei.tmdb.popular.PopularRsp
import cn.j1angvei.tmdb.popular.person.Person
import retrofit2.http.GET
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
}