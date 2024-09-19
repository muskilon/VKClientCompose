package com.example.composevkclient

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.lifecycle.ViewModelProvider
import com.example.composevkclient.ui.MainScreen
import com.example.composevkclient.ui.theme.ComposeVKClientTheme

class MainActivity : ComponentActivity() {
    private val viewModel by viewModels<MainActivityViewModel>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ComposeVKClientTheme {
                MainScreen(viewModel)
            }
        }
    }
}