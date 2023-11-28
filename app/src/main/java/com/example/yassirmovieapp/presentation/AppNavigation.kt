package com.example.yassirmovieapp.presentation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.example.yassirmovieapp.presentation.movie_details.MovieDetailsScreen
import com.example.yassirmovieapp.presentation.movies_list.MoviesScreen

@Composable
fun SetupNavigation(navController: NavHostController) {
    NavHost(
        navController = navController,
        startDestination = AppRoutes.MoviesList.route
    ) {
        composable(route = AppRoutes.MoviesList.route) {
            MoviesScreen(navController = navController)
        }

        composable(
            route = "${AppRoutes.MovieDetails.route}/{movieId}",
            arguments = listOf(
                navArgument("movieId") {
                    type = NavType.IntType
                    nullable = false
                    defaultValue = -1
                }
            )
        ) { navBackStackEntry ->
            MovieDetailsScreen(movieId = navBackStackEntry.arguments?.getInt("movieId") ?: -1)
        }
    }
}