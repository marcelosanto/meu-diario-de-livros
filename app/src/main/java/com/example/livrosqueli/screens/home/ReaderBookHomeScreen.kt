package com.example.livrosqueli.screens.home

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.rounded.Favorite
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.example.livrosqueli.components.BookRating
import com.example.livrosqueli.components.FABContent
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
        ReaderAppBar(title = "A.Reader", onNavigateToLoginScreen = onNavigateToLoginScreen)
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
        ListCard(
            book = MBook(
                id = "kasokoa",
                title = "Android em Acao",
                authors = "okaokas",
                notes = "uhuhu"
            ), onPressDetails = {})
    }
}

@Preview
@Composable
fun RoundedButton(label: String = "Reading", radius: Int = 29, onPress: () -> Unit = {}) {
    Surface(
        modifier = Modifier.clip(
            RoundedCornerShape(
                topStartPercent = radius,
                bottomEndPercent = radius
            )
        ),
        color = Color(0xFF92CBDF)
    ) {
        Column(
            modifier = Modifier
                .width(90.dp)
                .heightIn(40.dp)
                .clickable { onPress() },
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(text = label, style = TextStyle(color = Color.White, fontSize = 15.sp))
        }

    }
}

@Composable
fun ListCard(book: MBook, onPressDetails: (String) -> Unit) {
    val resources = LocalContext.current.resources
    val displayMetrics = resources.displayMetrics
    val screenWidth = displayMetrics.widthPixels / displayMetrics.density
    val spacing = 10.dp

    Card(
        shape = RoundedCornerShape(29.dp),
        backgroundColor = Color.White,
        elevation = 6.dp,
        modifier = Modifier
            .padding(16.dp)
            .height(242.dp)
            .width(202.dp)
            .clickable { onPressDetails(book.title.toString()) }
    ) {
        Column(
            modifier = Modifier.width(screenWidth.dp - (spacing * 2)),
            horizontalAlignment = Alignment.Start
        ) {
            Row(horizontalArrangement = Arrangement.Center) {
                AsyncImage(
                    model = "https://m.media-amazon.com/images/I/51RlAPJ0DzL._SX260_.jpg",
                    contentDescription = "Book image",
                    modifier = Modifier
                        .height(140.dp)
                        .width(100.dp)
                        .padding(4.dp)
                )

                Spacer(modifier = Modifier.width(50.dp))

                Column(
                    modifier = Modifier.padding(top = 25.dp),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Icon(
                        imageVector = Icons.Rounded.Favorite,
                        contentDescription = "Favorite icon",
                        modifier = Modifier.padding(bottom = 1.dp)
                    )
                    BookRating(score = 3.5)
                }
            }

            Text(
                text = "Book Title",
                modifier = Modifier.padding(4.dp),
                fontWeight = FontWeight.Bold,
                maxLines = 2,
                overflow = TextOverflow.Ellipsis
            )
            Text(
                text = "Authors: All...",
                modifier = Modifier.padding(4.dp), style = MaterialTheme.typography.caption
            )
        }

        Row(
            horizontalArrangement = Arrangement.End,
            verticalAlignment = Alignment.Bottom
        ) {
            RoundedButton(label = "Reading", radius = 70)
        }
    }
}


