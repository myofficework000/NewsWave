package com.example.newswithcleancode.repository

import com.example.newswithcleancode.model.LatestNews
import com.example.newswithcleancode.model.SearchNews
import com.example.newswithcleancode.utils.ApiResult

interface NewsRepository {
    suspend fun getLatestNews(language: String): ApiResult<LatestNews>
    suspend fun searchNews(
        keywords: String,
        start_date: String? = null,
        end_date: String? = null,
        category: String? = null,
        country: String? = null,
        language: String? = null
    ): ApiResult<SearchNews>
}