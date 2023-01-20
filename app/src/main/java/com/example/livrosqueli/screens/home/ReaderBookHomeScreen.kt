package com.example.livrosqueli.screens.home

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.livrosqueli.components.FABContent
import com.example.livrosqueli.components.ListCard
import com.example.livrosqueli.components.ReaderAppBar
import com.example.livrosqueli.components.TitleSection
import com.example.livrosqueli.model.MBook
import com.google.firebase.auth.FirebaseAuth

@Composable
fun ReaderBookHomeScreen(
    onNavigateToLoginScreen: () -> Unit,
    onNavigateToReaderStatsScreen: () -> Unit
) {
    Scaffold(topBar = {
        ReaderAppBar(title = "A.Reader", onNavigateToScreen = onNavigateToLoginScreen)
    }, floatingActionButton = {
        FABContent {}
    }) {
        Surface(
            modifier = Modifier
                .fillMaxSize()
                .padding(it)
        ) {
            HomeContent(onNavigateToReaderStatsScreen)
        }
    }
}

@Composable
fun HomeContent(onNavigateToReaderStatsScreen: () -> Unit) {
    val email = FirebaseAuth.getInstance().currentUser?.email
    val currentUserName = if (!email.isNullOrEmpty()) {
        email.split("@")[0]
    } else "N/A"

    val listOfBooks = listOf(
        MBook(
            id = "kasokoa",
            title = "Android em Acao",
            authors = "okaokas",
            notes = "uhuhu"
        ), MBook(
            id = "kasoko2a",
            title = "Android em Acao",
            authors = "okaokas",
            notes = "uhuhu"
        ), MBook(
            id = "kasoko3a",
            title = "Android em Acao",
            authors = "okaokas",
            notes = "uhuhu"
        ), MBook(
            id = "kasoko4a",
            title = "Android em Acao",
            authors = "okaokas",
            notes = "uhuhu"
        )
    )

    Column(Modifier.padding(2.dp), verticalArrangement = Arrangement.Top) {
        Row(Modifier.align(alignment = Alignment.Start)) {
            TitleSection(label = "Your reading \n " + " activity right now...")
            Spacer(modifier = Modifier.fillMaxWidth(0.7f))
            Column {
                Icon(
                    imageVector = Icons.Filled.AccountCircle,
                    contentDescription = "Profile image",
                    modifier = Modifier
                        .clickable {
                            onNavigateToReaderStatsScreen()
                        }
                        .size(45.dp),
                    tint = MaterialTheme.colors.secondaryVariant)

                Text(
                    text = currentUserName,
                    modifier = Modifier.padding(2.dp),
                    style = MaterialTheme.typography.overline,
                    color = Color.Red,
                    fontSize = 15.sp,
                    maxLines = 1,
                    overflow = TextOverflow.Clip
                )
                Divider()
            }
        }
        ReadingRightNowArea(books = listOfBooks, onNavigateToReaderStatsScreen)
    }
}

@Composable
fun ReadingRightNowArea(books: List<MBook>, onNavigateToReaderStatsScreen: () -> Unit) {
    ListCard(books.first(), onPressDetails = {})

    TitleSection(label = "Reading List")

    LazyRow {
        items(books) { book ->
            ListCard(book = book) {
                onNavigateToReaderStatsScreen()
            }

        }
    }
}



