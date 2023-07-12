package com.example.newswithcleancode.ui

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.SearchBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.paint
import androidx.compose.ui.layout.ContentScale
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
    if (vm.filterOpened) BackHandler { vm.toggleFilter(false) }
    Box(
        modifier = Modifier
            .fillMaxSize()
            .paint(
                painterResource(id = R.drawable.background),
                contentScale = ContentScale.FillBounds
            )
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            SearchBar(
                query = vm.searchQuery,
                onQueryChange = { vm.onQueryChange(it) },
                onSearch = { vm.searchNews(true) },
                active = vm.filterOpened,
                onActiveChange = { if (!it) vm.searchNews(true) },
                leadingIcon = {
                    if (vm.filterOpened)
                        IconButton(onClick = { vm.toggleFilter(false) }) {
                            Icon(painterResource(R.drawable.baseline_arrow_back_ios_24), null)
                        }
                    else
                        Icon(painterResource(R.drawable.baseline_search_24), null)
                },
                trailingIcon = {
                    IconButton(onClick = { vm.toggleFilter(!vm.filterOpened) }) {
                        Icon(painterResource(R.drawable.baseline_filter_list_24), null)
                    }
                }
            ) {

            }

            vm.searchResult?.let { SearchedNewsUi(data = it) }

            Headline(
                data = vm.latestNews,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp)
            )
        }
    }
}