package com.example.newswithcleancode.mapper

import com.example.newswithcleancode.model.SearchNews
import com.example.newswithcleancode.model.SearchNewsResponse


fun SearchNewsResponse.toDomain() = SearchNews(
    news = news.toDomain(),
    page = page,
    status = status
)

fun SearchNews.toResponse() = SearchNewsResponse(
    news = news.toResponse(),
    page = page,
    status = status
)