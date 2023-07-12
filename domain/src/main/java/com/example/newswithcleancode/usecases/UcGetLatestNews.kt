package com.example.newswithcleancode.usecases

import com.example.newswithcleancode.repository.NewsRepository
import javax.inject.Inject

class UcGetLatestNews @Inject constructor(
    private val repository: NewsRepository
) {
    suspend operator fun invoke(
        language: String
    ) = repository.getLatestNews(language)
}