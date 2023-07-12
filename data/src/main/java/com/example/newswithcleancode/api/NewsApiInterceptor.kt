package com.example.newswithcleancode.api

import com.example.newswithcleancode.DataConst.NEWS_TOKEN
import okhttp3.Interceptor
import okhttp3.Response

class NewsApiInterceptor: Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
        val currentRequest = chain.request().newBuilder()
        currentRequest.addHeader("Authorization", NEWS_TOKEN)
        val newRequest = currentRequest.build()
        return chain.proceed(newRequest)
    }
}
