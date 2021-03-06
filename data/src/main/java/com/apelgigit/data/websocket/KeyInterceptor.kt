package com.apelgigit.data.websocket

import com.apelgigit.commons.constants.Constants
import okhttp3.Interceptor
import okhttp3.Response

class ApiKeyInterceptor(private val apiKey: String) : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val url =
            chain.request().url().newBuilder().addQueryParameter(Constants.QUERY_PARAM_API_KEY, apiKey).build()
        return chain.proceed(
            chain.request()
                .newBuilder()
                .url(url)
                .build()
        )
    }
}