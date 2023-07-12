package com.example.newswithcleancode.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.SearchBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.newswithcleancode.R
import com.example.newswithcleancode.components.Headline
import com.example.newswithcleancode.components.SearchedNewsUi
import com.example.newswithcleancode.viewmodel.MainPageViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainPage(
    vm: MainPageViewModel
) {
    SearchBar(
        query = vm.searchQuery,
        onQueryChange = { vm.searchQuery = it; vm.searchNews(it) },
        onSearch = { vm.searchNews(it) },
        active = true,
        onActiveChange = {},
        leadingIcon = { Icon(painterResource(R.drawable.baseline_search_24), "") }
    ) {
        vm.searchResult?.let{ SearchedNewsUi(data = it) }

        Headline(
            data = vm.latestNews,
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp)
        )
    }
}