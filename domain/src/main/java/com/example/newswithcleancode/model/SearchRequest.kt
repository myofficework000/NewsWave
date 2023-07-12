package com.example.newswithcleancode.model

import com.example.newswithcleancode.utils.SearchRange

data class SearchRequest(
    val keywords: String,
    val dat_range: SearchRange = SearchRange.YEAR,
    val category: String? = null,
    val country: String? = null,
    val language: String? = null
)
