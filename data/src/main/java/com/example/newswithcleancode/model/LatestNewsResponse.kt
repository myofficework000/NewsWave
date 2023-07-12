package com.example.newswithcleancode.model

data class LatestNewsResponse(
    val news: List<NewsResponse>,
    val status: String
)