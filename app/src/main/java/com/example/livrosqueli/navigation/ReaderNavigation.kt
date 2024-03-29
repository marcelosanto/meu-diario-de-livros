package com.example.livrosqueli.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.livrosqueli.screens.ReaderSplashScreen
import com.example.livrosqueli.screens.home.ReaderBookHomeScreen
import com.example.livrosqueli.screens.login.ReaderBookLoginScreen
import com.example.livrosqueli.screens.search.ReaderBookSearchScreen
import com.example.livrosqueli.screens.stats.ReaderBookStatsScreen

@Composable
fun ReaderNavigation() {
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = ReaderScreens.SplashScreen.name) {
        composable(ReaderScreens.SplashScreen.name) {
            ReaderSplashScreen(
                onNavigateToLoginScreen = {
                    navController.navigate(ReaderScreens.LoginScreen.name)
                },
                onNavigateToBookHomeScreen = {
                    navController.navigate(ReaderScreens.ReaderHomeScreen.name)
                })
        }

        composable(ReaderScreens.LoginScreen.name) {
            ReaderBookLoginScreen(onNavigateToBookHomeScreen = {
                navController.navigate(ReaderScreens.ReaderHomeScreen.name)
            })
        }

        composable(ReaderScreens.ReaderHomeScreen.name) {
            ReaderBookHomeScreen(
                onNavigateToLoginScreen = {
                    navController.navigate(ReaderScreens.LoginScreen.name)
                },
                onNavigateToReaderStatsScreen = {
                    navController.navigate(ReaderScreens.ReaderStatsScreen.name)
                }
            )
        }

        composable(ReaderScreens.ReaderStatsScreen.name) {
            ReaderBookStatsScreen()
        }

        composable(ReaderScreens.SearchScreen.name) {
            ReaderBookSearchScreen(onNavigateToBookHomeScreen = {
                navController.navigate(ReaderScreens.ReaderHomeScreen.name)
            })
        }

    }
}