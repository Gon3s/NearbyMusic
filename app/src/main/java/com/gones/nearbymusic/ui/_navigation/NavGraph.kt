package com.gones.nearbymusic.ui._navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.gones.nearbymusic.ui.home.HomeScreen
import com.gones.nearbymusic.ui.login.LoginScreen
import com.gones.nearbymusic.ui.presentation.PresentationScreen
import com.gones.nearbymusic.ui.register.RegisterScreen

@Composable
fun NavGraph(
    navController: NavHostController,
) {
    NavHost(
        navController,
        startDestination = Screens.Presentation.route
    ) {
        composable(Screens.Presentation.route) {
            PresentationScreen(
                navController = navController
            )
        }

        composable(Screens.Login.route) {
            LoginScreen(
                navController = navController
            )
        }

        composable(Screens.Register.route) {
            RegisterScreen(
                navController = navController
            )
        }

        composable(Screens.Home.route) {
            HomeScreen(
                navController = navController
            )
        }
    }
}