package cn.j1angvei.tmdb.di

import cn.j1angvei.tmdb.CALL_TIME_OUT
import cn.j1angvei.tmdb.TMDB_BASE_URL
import cn.j1angvei.tmdb.api.HeadersInterceptor
import cn.j1angvei.tmdb.api.TmdbApiService
import com.google.gson.FieldNamingPolicy
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {
    @Singleton
    @Provides
    fun provideGson(): Gson {
        return GsonBuilder().setDateFormat("yyyy-MM-dd")
            .setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES).create()
    }

    @Singleton
    @Provides
    fun provideOkHttpClient(): OkHttpClient {
        return OkHttpClient.Builder().callTimeout(CALL_TIME_OUT, TimeUnit.SECONDS)
            .addInterceptor(HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BASIC))
            .addInterceptor(HeadersInterceptor()).build()
    }

    @Singleton
    @Provides
    fun provideTmdbApi(gson: Gson, client: OkHttpClient): TmdbApiService {
        return Retrofit.Builder().baseUrl(TMDB_BASE_URL)
            .addConverterFactory(GsonConverterFactory.create(gson)).client(client).build()
            .create(TmdbApiService::class.java)
    }

}