package com.example.littlelemon

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController

@Composable
fun NavigationComposable(navController: NavHostController, database: AppDatabase) {

    NavHost(
        navController = navController,
        startDestination = Onboarding.route
    ) {
        composable(Onboarding.route) { Onboarding(navController = navController) }
        composable(Home.route) { Home(navController = navController, database) }
        composable(Profile.route) { Profile(navController = navController) }
    }
}

