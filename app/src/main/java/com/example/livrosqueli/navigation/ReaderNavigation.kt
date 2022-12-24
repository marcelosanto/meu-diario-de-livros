package com.example.livrosqueli.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.livrosqueli.screens.ReaderSplashScreen
import com.example.livrosqueli.screens.home.ReaderBookHomeScreen
import com.example.livrosqueli.screens.login.ReaderBookLoginScreen

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
            ReaderBookHomeScreen {
                navController.navigate(ReaderScreens.LoginScreen.name)
            }
        }

    }
}