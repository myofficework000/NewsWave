package com.example.newswithcleancode.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.newswithcleancode.model.LatestNews
import com.example.newswithcleancode.model.News
import com.example.newswithcleancode.model.SearchNews
import com.example.newswithcleancode.model.SearchRequest
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
    // Usable data
    var latestNews by mutableStateOf<ApiResult<LatestNews>>(ApiResult.Pending())
        private set
    var searchResult by mutableStateOf<ApiResult<SearchNews>?>(null)
    //      current is the one prepared to be sent to the domain layer, last
    //          is the one that's actually sent. These 2 are here to make
    //          sure duplicate requests aren't sent.
    private var currentQuery = SearchRequest("")
    private var lastQuery = currentQuery

    // For UI states
    var searchQuery by mutableStateOf("")
    var filterOpened by mutableStateOf(false)
        private set

    // Others
    private var searchJob: Job? = null

    init { getLatestNews() }

    fun onQueryChange(query: String) {
        searchQuery = query
        searchNews()
        currentQuery = currentQuery.copy(keywords = query)
    }

    fun searchNews(immediate: Boolean = false) {
        searchJob?.cancel()
        searchJob = viewModelScope.launch(ioDispatcher) {
            if (!immediate) delay(1500)
            if (currentQuery != lastQuery) {
                lastQuery = currentQuery
                searchResult = ApiResult.Pending()
                with(searchNewsUc(currentQuery)) { searchResult = this }
            }
        }
    }

    fun toggleFilter(open: Boolean) { filterOpened = open }

    fun getLatestNews() {
        viewModelScope.launch(ioDispatcher) {
            with(latestNewsUc("en")) {
                latestNews = this
            }
        }
    }
}