package com.example.yassirmovieapp.presentation.movies_list

import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.paging.LoadState
import androidx.paging.compose.collectAsLazyPagingItems
import com.example.yassirmovieapp.R
import com.example.yassirmovieapp.general.CustomAppException
import com.example.yassirmovieapp.presentation.AppRoutes

@Composable
fun MoviesScreen(
    navController: NavController
) {
    val context = LocalContext.current
    val viewModel: MoviesViewModel = hiltViewModel()
    val movies = viewModel.moviesPagingFlow.collectAsLazyPagingItems()
    val state = viewModel.state.collectAsState()

    Surface(
        modifier = Modifier.fillMaxSize(),
        color = MaterialTheme.colorScheme.background
    ) {
        LaunchedEffect(key1 = movies.loadState) {
            when (movies.loadState.refresh) {
                is LoadState.Loading -> viewModel.onEvent(MoviesEvent.ChangeLoading(true))
                is LoadState.NotLoading -> viewModel.onEvent(MoviesEvent.ChangeLoading(false))
                else -> {
                    val errorState = when {
                        movies.loadState.refresh is LoadState.Error -> movies.loadState.refresh as LoadState.Error
                        movies.loadState.append is LoadState.Error -> movies.loadState.append as LoadState.Error
                        movies.loadState.prepend is LoadState.Error -> movies.loadState.prepend as LoadState.Error
                        else -> null
                    }
                    errorState?.let {
                        viewModel.onEvent(MoviesEvent.SetError(it.error as CustomAppException))
                        viewModel.onEvent(MoviesEvent.ChangeLoading(false))
                    }
                }
            }
        }

        LaunchedEffect(key1 = state.value.error) {
            state.value.error?.let {
                Toast.makeText(
                    context,
                    it.asString(context),
                    Toast.LENGTH_LONG
                ).show()
            }
        }


        Box(
            modifier = Modifier
                .fillMaxSize()
        ) {
            if (state.value.isLoading) {
                CircularProgressIndicator(
                    modifier = Modifier.align(Alignment.Center)
                )
            } else {
                if (movies.itemCount < 1) {
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
                        Spacer(modifier = Modifier.height(8.dp))
                        Button(onClick = {
                            movies.refresh()
                        }) {
                            Text(text = "Refresh")
                        }
                    }
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
                                    modifier = Modifier.fillMaxWidth()
                                ) {
                                    navController.navigate(route = "${AppRoutes.MovieDetails.route}/${movie.id}")
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}
