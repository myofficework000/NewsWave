package com.example.newswithcleancode

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
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
                MainPage(viewModel())
            }
        }
    }
}