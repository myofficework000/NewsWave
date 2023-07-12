package com.example.newswithcleancode.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Divider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.newswithcleancode.model.SearchNews
import com.example.newswithcleancode.utils.ApiResult

@Composable
fun SearchedNewsUi(
    data: ApiResult<SearchNews>,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        when (data) {
            is ApiResult.Failure -> {}
            is ApiResult.Pending -> {
                Box(Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                    CircularProgressIndicator(Modifier.size(256.dp), strokeWidth = 16.dp)
                }
            }
            is ApiResult.Success -> {
                LazyColumn(
                    Modifier.fillMaxWidth(),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    itemsIndexed(data.body.news) {ix, it ->
                        Column {
                            NewsBrief(data = it)
                            if (ix < data.body.news.lastIndex)
                                Divider(Modifier.padding(vertical = 8.dp))
                        }
                    }

                    item {
                        TextButton(onClick = {  }) {
                            Text("Want to see more? Click here")
                        }
                    }
                }
            }
        }
    }
}