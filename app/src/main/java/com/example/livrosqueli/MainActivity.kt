package com.example.livrosqueli

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.example.livrosqueli.screens.main.ReaderMainScreen
import com.example.livrosqueli.screens.main.ReaderMainViewModel
import com.example.livrosqueli.ui.theme.LivrosQueLiTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            LivrosQueLiTheme {
                ReaderMainScreen(mainViewModel = ReaderMainViewModel(), applicationContext)


            }
        }
    }
}

