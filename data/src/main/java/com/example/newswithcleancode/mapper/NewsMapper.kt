package com.example.newswithcleancode.mapper

import com.example.newswithcleancode.fixNullData
import com.example.newswithcleancode.model.News
import com.example.newswithcleancode.model.NewsResponse

fun NewsResponse.toDomain() = News(
    author, category, description, id, image, language, published, title, url
).fixNullData()

fun News.toResponse() = NewsResponse(
    author, category, description, id, image, language, published, title, url
)

fun List<NewsResponse>.toDomain() = map{ it.toDomain() }
fun List<News>.toResponse() = map{ it.toResponse() }