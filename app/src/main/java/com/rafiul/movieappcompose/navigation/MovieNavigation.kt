package com.rafiul.movieappcompose.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.rafiul.movieappcompose.screens.details.DetailsScreen
import com.rafiul.movieappcompose.screens.home.HomeScreen


@Composable
fun MovieNavigation() {
    val navController = rememberNavController()
    NavHost(
        navController = navController,
        startDestination = MovieScreens.HomeScreen.name
    ) {

        composable(route = MovieScreens.HomeScreen.name) {
            //here we pass where it should lead us
            HomeScreen(navController = navController)
        }

        composable(route = MovieScreens.DetailsScreen.name) {
            DetailsScreen(navController = navController)
        }

    }
}