package com.example.newswithcleancode.api

import okhttp3.Interceptor
import okhttp3.Response

class NewsApiInterceptor(
    private val apiToken: String
): Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val currentRequest = chain.request().newBuilder()
        currentRequest.addHeader("Authorization", apiToken)
        val newRequest = currentRequest.build()
        return chain.proceed(newRequest)
    }
}
