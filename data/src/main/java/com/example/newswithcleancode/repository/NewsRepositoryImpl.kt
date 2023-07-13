package com.example.newswithcleancode.repository

import com.example.newswithcleancode.api.ApiNews
import com.example.newswithcleancode.mapper.toDomain
import com.example.newswithcleancode.model.LatestNews
import com.example.newswithcleancode.model.SearchNews
import com.example.newswithcleancode.utils.ApiResult
import javax.inject.Inject

/**
 * Implementation of the NewsRepository interface.
 *
 * @param api The ApiNews instance used for making API requests.
 */
class NewsRepositoryImpl @Inject constructor(
    private val api: ApiNews
) : NewsRepository {

    /**
     * Retrieves the latest news based on the specified language.
     *
     * @param language The language parameter used for filtering the news.
     * @return An ApiResult object containing the latest news as a success or an error message as a failure.
     */
    override suspend fun getLatestNews(language: String): ApiResult<LatestNews> =
        with(api.getLatestNews(language)) {
            body()?.run { ApiResult.Success(toDomain()) } ?: ApiResult.Failure(
                errorBody()?.string() ?: ""
            )
        }

    /**
     * Searches news based on the specified parameters.
     *
     * @param keywords The keywords used for searching news.
     * @param start_date The start date used for filtering news by date.
     * @param end_date The end date used for filtering news by date.
     * @param category The category used for filtering news by category.
     * @param country The country used for filtering news by country.
     * @param language The language used for filtering news by language.
     * @return An ApiResult object containing the search results as a success or an error message as a failure.
     */
    override suspend fun searchNews(
        keywords: String,
        start_date: String?,
        end_date: String?,
        category: String?,
        country: String?,
        language: String?
    ): ApiResult<SearchNews> =
        with(
            api.searchNews(
                keywords,
                start_date,
                end_date,
                category,
                country,
                language
            )
        ) {
            body()?.run { ApiResult.Success(toDomain()) } ?: ApiResult.Failure(
                errorBody()?.string() ?: ""
            )
        }
}