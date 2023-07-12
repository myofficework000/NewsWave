package com.example.newswithcleancode.api

import com.example.newswithcleancode.DataConst
import com.example.newswithcleancode.model.LatestNewsResponse
import com.example.newswithcleancode.model.SearchNewsResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiNews {
    @GET(DataConst.NEWS_LATEST_ENDPOINT)
    suspend fun getLatestNews(
        @Query("language") language: String
    ): Response<LatestNewsResponse>

    @GET(DataConst.NEWS_SEARCH_ENDPOINT)
    suspend fun searchNews(
        @Query("keywords") keywords: String,
        @Query("start_date") start_date: String? = null,
        @Query("end_date") end_date: String? = null,
        @Query("category") category: String? = null,
        @Query("country") country: String? = null,
        @Query("language") language: String? = null
    ): Response<SearchNewsResponse>
}