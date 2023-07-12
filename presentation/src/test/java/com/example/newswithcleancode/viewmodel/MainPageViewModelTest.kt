package com.example.newswithcleancode.viewmodel

import com.example.newswithcleancode.model.LatestNews
import com.example.newswithcleancode.model.News
import com.example.newswithcleancode.model.SearchNews
import com.example.newswithcleancode.usecases.UcGetLatestNews
import com.example.newswithcleancode.usecases.UcSearchNews
import com.example.newswithcleancode.utils.ApiResult
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.advanceUntilIdle
import kotlinx.coroutines.test.runTest
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test

@OptIn(ExperimentalCoroutinesApi::class)
class MainPageViewModelTest {
    private val latestNewsUc = mockk<UcGetLatestNews>()
    private val searchNewsUc = mockk<UcSearchNews>()
    private val ioDispatcher = StandardTestDispatcher()
    private val vm = MainPageViewModel(
        latestNewsUc,
        searchNewsUc,
        ioDispatcher
    )

    @Before
    fun setUp() {
        // This runs on init(), so every test needs a default response.
        coEvery { latestNewsUc(any()) } returns latestNewsEmpty
    }

    @Test
    fun onQueryChange() {
        vm.onQueryChange("test1")
        assertEquals("test1", vm.searchQuery)
    }

    @Test
    fun searchNews() = runTest(ioDispatcher) {
        coEvery { searchNewsUc(any()) } returns successSearch1

        vm.onQueryChange("test2")
        vm.searchNews()
        advanceUntilIdle()

        coVerify { searchNewsUc(any()) }
        assertTrue(vm.searchResult is ApiResult.Success)
        assertEquals(news2, (vm.searchResult as ApiResult.Success).body.news[1])
    }

    @Test
    fun searchNewsImmediate() = runTest(ioDispatcher) {
        coEvery { searchNewsUc(any()) } returns successSearch1

        vm.onQueryChange("test3")
        vm.searchNews(true)
        advanceUntilIdle()

        coVerify { searchNewsUc(any()) }
        assertTrue(vm.searchResult is ApiResult.Success)
        assertEquals(news2, (vm.searchResult as ApiResult.Success).body.news[1])
    }

    @Test
    fun toggleFilter() {
        vm.toggleFilter(true)

        assertTrue(vm.filterOpened)

        vm.toggleFilter(false)

        assertFalse(vm.filterOpened)
    }

    @Test
    fun getLatestNews() = runTest(ioDispatcher) {
        coEvery { latestNewsUc(any()) } returns latestNews1

        vm.getLatestNews()
        advanceUntilIdle()

        coVerify { latestNewsUc(any()) }
        assertTrue(vm.latestNews is ApiResult.Success)
        assertEquals(news2, (vm.latestNews as ApiResult.Success).body.news[1])
    }

    companion object {
        private val news1 = News(
            author = "",
            category = listOf(),
            description = "",
            id = "",
            image = null,
            language = "",
            published = "",
            title = "news1",
            url = ""
        )

        private val news2 = News(
            author = "you",
            category = listOf(),
            description = "",
            id = "",
            image = null,
            language = "",
            published = "",
            title = "news2",
            url = ""
        )

        private val successSearch1 = ApiResult.Success(SearchNews(
            listOf(news1, news2), 1, "ok"
        ))

        private val latestNews1 = ApiResult.Success(LatestNews(
            listOf(news1, news2), "ok"
        ))

        private val latestNewsEmpty = ApiResult.Success(LatestNews(listOf(), "ok"))
    }
}