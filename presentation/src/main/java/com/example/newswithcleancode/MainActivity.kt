package com.example.newswithcleancode

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.newswithcleancode.ui.MainPage
import com.example.newswithcleancode.ui.theme.NewsWithCleanCodeTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            NewsWithCleanCodeTheme {
                Surface(Modifier.background(MaterialTheme.colorScheme.background)) {
                    MainPage(viewModel())
                }
            }
        }
    }
}