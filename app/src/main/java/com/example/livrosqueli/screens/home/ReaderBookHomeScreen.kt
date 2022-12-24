package com.example.livrosqueli.screens.home

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Logout
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
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

    Column(Modifier.padding(2.dp), verticalArrangement = Arrangement.SpaceEvenly) {
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
    }
}

@Composable
fun ReaderAppBar(
    title: String,
    showProfile: Boolean = true,
    onNavigateToLoginScreen: () -> Unit
) {
    TopAppBar(
        title = {
            Row(verticalAlignment = Alignment.CenterVertically) {
                if (showProfile) {
                    Icon(
                        imageVector = Icons.Filled.Favorite,
                        contentDescription = "Logo icon",
                        modifier = Modifier
                            .clip(RoundedCornerShape(12.dp))
                            .scale(0.7f)
                    )
                }
                Text(
                    text = title,
                    color = Color.Red.copy(alpha = 0.7f),
                    style = TextStyle(fontWeight = FontWeight.Bold, fontSize = 20.sp)
                )
                Spacer(modifier = Modifier.width(150.dp))
            }
        },
        actions = {
            IconButton(onClick = {
                FirebaseAuth.getInstance().signOut().run {
                    onNavigateToLoginScreen()
                }
            }) {
                Icon(
                    imageVector = Icons.Filled.Logout,
                    contentDescription = "logout icon",
                    tint = Color.Green.copy(alpha = 0.4f)
                )
            }
        },
        backgroundColor = Color.Transparent,
        elevation = 0.dp
    )
}

@Composable
fun TitleSection(modifier: Modifier = Modifier, label: String) {
    Surface(modifier = modifier.padding(start = 5.dp, top = 1.dp)) {
        Column {
            Text(
                text = label,
                fontSize = 19.sp,
                fontStyle = FontStyle.Normal,
                textAlign = TextAlign.Left
            )
        }
    }
}

@Composable
fun ReadingRightNowArea(books: List<MBook>) {

}


@Composable
fun FABContent(onTap: () -> Unit) {
    FloatingActionButton(
        onClick = { onTap() },
        shape = RoundedCornerShape(50.dp),
        backgroundColor = Color(0xFF92CBDF)
    ) {
        Icon(
            imageVector = Icons.Default.Add,
            contentDescription = "Add a book",
            tint = Color.White
        )
    }
}
