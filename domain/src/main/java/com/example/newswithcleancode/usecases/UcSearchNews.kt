package com.example.newswithcleancode.usecases

import com.example.newswithcleancode.model.SearchNews
import com.example.newswithcleancode.model.SearchRequest
import com.example.newswithcleancode.repository.NewsRepository
import com.example.newswithcleancode.utils.ApiResult
import com.example.newswithcleancode.utils.toDatePair
import javax.inject.Inject

class UcSearchNews @Inject constructor(
    private val repository: NewsRepository
) {
    suspend operator fun invoke(query: SearchRequest): ApiResult<SearchNews> {
        val dateRange = query.dat_range.toDatePair()

        return repository.searchNews(
            query.keywords,
            dateRange.first,
            dateRange.second,
            query.category,
            query.country,
            query.language
        )
    }
}