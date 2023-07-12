package com.example.newswithcleancode.model

data class SearchNews(
    val news: List<News>,
    val page: Int,
    val status: String
)