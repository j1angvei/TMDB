package cn.j1angvei.tmdb.api

import cn.j1angvei.tmdb.TMDB_ACCESS_TOKEN
import okhttp3.Interceptor
import okhttp3.Response

class HeadersInterceptor : Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
        val originalRequest = chain.request()
        val modifiedRequest =
            originalRequest.newBuilder().header("Authorization", "Bearer $TMDB_ACCESS_TOKEN")
                .header("Accept", "application/json").build()
        return chain.proceed(modifiedRequest)
    }
}