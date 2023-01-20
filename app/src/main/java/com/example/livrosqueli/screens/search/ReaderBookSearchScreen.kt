package com.example.livrosqueli.screens.search

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Scaffold
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.livrosqueli.components.ReaderAppBar

@Preview
@Composable
fun ReaderBookSearchScreen(onNavigateToBookHomeScreen: () -> Unit = {}) {
    Scaffold(topBar = {
        ReaderAppBar(
            title = "Search Books",
            icon = Icons.Default.ArrowBack,
            showProfile = false,
            onNavigateToScreen = onNavigateToBookHomeScreen
        )
    }) {
        Box(modifier = Modifier.padding(it))
    }
}