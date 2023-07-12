package com.example.newswithcleancode

import com.example.newswithcleancode.model.News
import com.example.newswithcleancode.model.NewsResponse

fun NewsResponse.fixNullData() = copy(
    image = image?.takeIf { it.contains("https://") }
)

fun News.fixNullData() = copy(
    image = image?.takeIf { it.contains("https://") }
)