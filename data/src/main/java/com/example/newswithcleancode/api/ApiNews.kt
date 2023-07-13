package com.example.newswithcleancode.api

import com.example.newswithcleancode.DataConst
import com.example.newswithcleancode.model.LatestNewsResponse
import com.example.newswithcleancode.model.SearchNewsResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query


/**
Interface for accessing news-related APIs.
 */
interface ApiNews {

    /**
    Retrieves the latest news.
    @param language The language of the news to retrieve.
    @return A response containing the latest news.
     */
    @GET(DataConst.NEWS_LATEST_ENDPOINT)
    suspend fun getLatestNews(
        @Query("language") language: String
    ): Response<LatestNewsResponse>

    /**
    Searches for news based on the provided criteria.
    @param keywords The keywords to search for in the news articles.
    @param start_date The start date of the news articles to retrieve. (Optional)
    @param end_date The end date of the news articles to retrieve. (Optional)
    @param category The category of the news articles to retrieve. (Optional)
    @param country The country of the news articles to retrieve. (Optional)
    @param language The language of the news articles to retrieve. (Optional)
    @return A response containing the search results.
     */
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