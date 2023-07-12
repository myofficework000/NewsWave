package com.example.newswithcleancode.mapper

import com.example.newswithcleancode.model.LatestNews
import com.example.newswithcleancode.model.LatestNewsResponse

fun LatestNewsResponse.toDomain() = LatestNews(
    news = news.toDomain(),
    status = status
)

fun LatestNews.toResponse() = LatestNewsResponse(
    news = news.toResponse(),
    status = status
)