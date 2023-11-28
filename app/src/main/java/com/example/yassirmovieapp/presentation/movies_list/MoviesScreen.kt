package com.example.yassirmovieapp.presentation.movies_list

import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.paging.LoadState
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.collectAsLazyPagingItems
import com.example.yassirmovieapp.R
import com.example.yassirmovieapp.domain.model.Movie
import com.example.yassirmovieapp.general.CustomAppException
import com.example.yassirmovieapp.presentation.AppRoutes
import com.google.accompanist.swiperefresh.SwipeRefresh
import com.google.accompanist.swiperefresh.rememberSwipeRefreshState

@Composable
fun MoviesScreen(
    navController: NavController
) {
    val context = LocalContext.current
    val viewModel: MoviesViewModel = hiltViewModel()
    val movies = viewModel.moviesPagingFlow.collectAsLazyPagingItems()
    val swipeRefreshState = rememberSwipeRefreshState(isRefreshing = movies.loadState.refresh is LoadState.Loading)

    Surface(
        modifier = Modifier.fillMaxSize(),
        color = MaterialTheme.colorScheme.background
    ) {
        SwipeRefresh(
            state = swipeRefreshState,
            onRefresh = {
                movies.refresh()
            },
            modifier = Modifier
                .fillMaxSize()
        ) {
            if (movies.itemCount == 0 && movies.loadState.source.refresh !is LoadState.Loading) {
                EmptyView(movies = movies)
            } else {
                LazyColumn(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(16.dp),
                    verticalArrangement = Arrangement.spacedBy(16.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    items(
                        count = movies.itemCount
                    ) { index ->
                        movies[index]?.let { movie ->
                            MovieItem(
                                movie = movie,
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .clickable {
                                        navController.navigate(route = "${AppRoutes.MovieDetails.route}/${movie.id}")
                                    }
                            )
                        }
                    }
                }
            }
        }
    }

    LaunchedEffect(
        key1 = movies.loadState.refresh,
        block = {
            if (movies.loadState.refresh is LoadState.Error) {
                Toast.makeText(
                    context,
                    ((movies.loadState.refresh as LoadState.Error).error as CustomAppException).uiText.asString(context),
                    Toast.LENGTH_LONG
                ).show()
            }
        }
    )
}

@Composable
fun EmptyView(movies: LazyPagingItems<Movie>) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(painter = painterResource(id = R.drawable.img_empty_data), contentDescription = stringResource(R.string.empty_data))
        Spacer(modifier = Modifier.height(16.dp))
        Text(text = stringResource(R.string.no_results_to_show), style = MaterialTheme.typography.titleLarge)
        Spacer(modifier = Modifier.height(8.dp))
        Text(text = stringResource(R.string.please_refresh_or_try_again_later), style = MaterialTheme.typography.bodyMedium)
        Spacer(modifier = Modifier.height(32.dp))
        Button(onClick = {
            movies.refresh()
        }) {
            Text(text = "Refresh")
        }
    }
}
