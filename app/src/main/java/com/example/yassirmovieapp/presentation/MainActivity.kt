package com.example.yassirmovieapp.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.yassirmovieapp.presentation.movie_details.MovieDetailsScreen
import com.example.yassirmovieapp.presentation.movies_list.MoviesScreen
import com.example.yassirmovieapp.presentation.ui.theme.YassirMovieAppTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            YassirMovieAppTheme {
                val rememberedNavController = rememberNavController()
                NavHost(
                    navController = rememberedNavController,
                    startDestination = AppRoutes.MoviesList.route
                ) {
                    composable(route = AppRoutes.MoviesList.route) {
                        MoviesScreen(navController = rememberedNavController)
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
        }
    }
}