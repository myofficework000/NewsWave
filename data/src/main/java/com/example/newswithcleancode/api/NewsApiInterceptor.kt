package com.example.newswithcleancode.api

import com.example.newswithcleancode.DataConst.AUTHORIZATION
import okhttp3.Interceptor
import okhttp3.Response

/**
 * Interceptor implementation for adding authorization header to network requests made to the News API.
 *
 * @param apiToken The API token used for authorization.
 */
class NewsApiInterceptor(
    private val apiToken: String
) : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val currentRequest = chain.request().newBuilder()
        currentRequest.addHeader(AUTHORIZATION, apiToken)
        val newRequest = currentRequest.build()
        return chain.proceed(newRequest)
    }
}
