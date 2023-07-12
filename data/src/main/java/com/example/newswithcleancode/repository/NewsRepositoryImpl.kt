package com.example.newswithcleancode.repository

import com.example.newswithcleancode.api.ApiNews
import com.example.newswithcleancode.mapper.toDomain
import com.example.newswithcleancode.model.LatestNews
import com.example.newswithcleancode.model.SearchNews
import com.example.newswithcleancode.utils.ApiResult
import javax.inject.Inject

class NewsRepositoryImpl @Inject constructor(
    private val api: ApiNews
): NewsRepository {
    override suspend fun getLatestNews(language: String): ApiResult<LatestNews> =
        with (api.getLatestNews(language)) {
            body()?.run { ApiResult.Success(toDomain()) } ?:
                ApiResult.Failure(errorBody()?.string() ?: "")
        }

    override suspend fun searchNews(
        keywords: String,
        start_date: String?,
        end_date: String?,
        category: String?,
        country: String?,
        language: String?
    ): ApiResult<SearchNews> =
        with (api.searchNews(
            keywords,
            start_date,
            end_date,
            category,
            country,
            language
        )) {
            body()?.run { ApiResult.Success(toDomain()) } ?:
            ApiResult.Failure(errorBody()?.string() ?: "")
        }
}