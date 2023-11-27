package com.example.yassirmovieapp.data.repository

import androidx.paging.ExperimentalPagingApi
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.map
import com.example.yassirmovieapp.data.local.database.MovieAppDatabase
import com.example.yassirmovieapp.data.mapper.toMovie
import com.example.yassirmovieapp.data.mapper.toMovieEntity
import com.example.yassirmovieapp.data.remote.api.MovieAppApi
import com.example.yassirmovieapp.data.remote_mediator.MoviesRemoteMediator
import com.example.yassirmovieapp.domain.model.Movie
import com.example.yassirmovieapp.domain.repository.MovieRepository
import com.example.yassirmovieapp.general.Resource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class MovieRepositoryImp @Inject constructor(
    private val movieAppDatabase: MovieAppDatabase,
    private val movieAppApi: MovieAppApi
) : MovieRepository {

    @OptIn(ExperimentalPagingApi::class)
    override fun getMovies(): Flow<PagingData<Movie>> {
        return Pager(
            config = PagingConfig(
                pageSize = 20
            ),
            remoteMediator = MoviesRemoteMediator(
                movieAppDatabase = movieAppDatabase,
                movieAppApi = movieAppApi
            ),
            pagingSourceFactory = { movieAppDatabase.movieDao.pagingSource() }
        ).flow.map { pagingData ->
            pagingData.map { movieEntity -> movieEntity.toMovie() }
        }.flowOn(Dispatchers.IO)
    }

    override fun getMovieDetails(movieId: Int): Flow<Resource<Movie?>> {
        return cachedDataWithApiBackup(
            loadFromCache = {
                movieAppDatabase.movieDao.getMovieById(movieId = movieId)?.toMovie()
            },
            networkCall = {
                movieAppApi.getMovieDetails(movieId = movieId)
            },
            cacheOperation = {
                movieAppDatabase.movieDao.upsertMovie(it.toMovieEntity())
            }
        )
    }
}