package cn.j1angvei.tmdb

import com.squareup.moshi.Moshi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {
    @Singleton
    @Provides
    fun provideMoshi(): Moshi {
        return Moshi.Builder().build()
    }

    @Singleton
    @Provides
    fun provideOkHttpClient(): OkHttpClient {
        return OkHttpClient.Builder()
            .addInterceptor(HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BASIC))
            .addInterceptor(HeadersInterceptor()).build()
    }

    @Singleton
    @Provides
    fun provideTmdbApi(moshi: Moshi, client: OkHttpClient): TmdbApiService {
        return Retrofit.Builder().baseUrl(TMDB_BASE_URL)
            .addConverterFactory(MoshiConverterFactory.create(moshi)).client(client).build()
            .create(TmdbApiService::class.java)
    }

}