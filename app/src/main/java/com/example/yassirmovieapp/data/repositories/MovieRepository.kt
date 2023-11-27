package com.example.yassirmovieapp.data.repositories

import androidx.paging.ExperimentalPagingApi
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.map
import com.example.yassirmovieapp.data.local.database.MovieAppDatabase
import com.example.yassirmovieapp.data.mapper.toMovie
import com.example.yassirmovieapp.data.remote.api.MovieAppApi
import com.example.yassirmovieapp.data.remote_mediator.MovieRemoteMediator
import com.example.yassirmovieapp.domain.model.Movie
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class MovieRepository @Inject constructor(
    private val movieAppDatabase: MovieAppDatabase,
    private val movieAppApi: MovieAppApi
) {

    @OptIn(ExperimentalPagingApi::class)
    fun getMovies(): Flow<PagingData<Movie>> {
        return Pager(
            config = PagingConfig(
                pageSize = 20
            ),
            remoteMediator = MovieRemoteMediator(
                movieAppDatabase = movieAppDatabase,
                movieAppApi = movieAppApi
            ),
            pagingSourceFactory = { movieAppDatabase.movieDao.pagingSource() }
        ).flow.map { pagingData ->
            pagingData.map { movieEntity -> movieEntity.toMovie() }
        }.flowOn(Dispatchers.IO)
    }
}