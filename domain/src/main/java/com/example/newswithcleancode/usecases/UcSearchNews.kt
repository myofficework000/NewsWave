package com.example.newswithcleancode.usecases

import com.example.newswithcleancode.repository.NewsRepository
import com.example.newswithcleancode.utils.formatDate
import javax.inject.Inject

class UcSearchNews @Inject constructor(
    private val repository: NewsRepository
) {
    suspend operator fun invoke(
        keywords: String,
        start_date: Long? = null,
        end_date: Long? = null,
        category: String? = null,
        country: String? = null,
        language: String? = null
    ) = repository.searchNews(
        keywords,
        start_date?.formatDate(),
        end_date?.formatDate(),
        category,
        country,
        language
    )
}