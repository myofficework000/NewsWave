package com.example.newswithcleancode.mapper

import com.example.newswithcleancode.model.LatestNews
import com.example.newswithcleancode.model.LatestNewsResponse

/**
 * Converts the data from the LatestNewsResponse model to the corresponding domain model.
 *
 * @return The converted LatestNews domain model.
 */
fun LatestNewsResponse.toDomain() = LatestNews(
    news = news.toDomain(),
    status = status
)

/**
 * Converts the data from the LatestNews domain model to the corresponding response model.
 *
 * @return The converted LatestNewsResponse response model.
 */
fun LatestNews.toResponse() = LatestNewsResponse(
    news = news.toResponse(),
    status = status
)