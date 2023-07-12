package com.example.newswithcleancode.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.newswithcleancode.model.LatestNews
import com.example.newswithcleancode.model.News
import com.example.newswithcleancode.model.SearchNews
import com.example.newswithcleancode.usecases.UcGetLatestNews
import com.example.newswithcleancode.usecases.UcSearchNews
import com.example.newswithcleancode.utils.ApiResult
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainPageViewModel @Inject constructor(
    private val latestNewsUc: UcGetLatestNews,
    private val searchNewsUc: UcSearchNews,
    private val ioDispatcher: CoroutineDispatcher
): ViewModel() {
    var searchQuery by mutableStateOf("")
    var latestNews by mutableStateOf<ApiResult<LatestNews>>(ApiResult.Pending())
        private set

    var searchResult by mutableStateOf<ApiResult<SearchNews>?>(null)
    private var searchJob: Job? = null

    init { getLatestNews() }

    fun searchNews(query: String) {
        searchJob?.cancel()
        searchJob = viewModelScope.launch(ioDispatcher) {
            delay(1500)
            searchResult = ApiResult.Pending()
            with(searchNewsUc(keywords = query)) { searchResult = this }
        }
    }

    private fun getLatestNews() {
        viewModelScope.launch(ioDispatcher) {
            with(latestNewsUc("en")) {
                latestNews = this
            }
        }
    }
}