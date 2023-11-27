package com.example.yassirmovieapp.data.remote_mediator

import androidx.paging.ExperimentalPagingApi
import androidx.paging.LoadType
import androidx.paging.PagingState
import androidx.paging.RemoteMediator
import androidx.room.withTransaction
import com.example.yassirmovieapp.data.local.database.MovieAppDatabase
import com.example.yassirmovieapp.data.local.entity.MovieEntity
import com.example.yassirmovieapp.data.local.entity.RemoteKeyEntity
import com.example.yassirmovieapp.data.mapper.toMovieEntity
import com.example.yassirmovieapp.data.remote.api.MovieAppApi
import com.example.yassirmovieapp.general.AppExceptionMapper

@OptIn(ExperimentalPagingApi::class)
class MovieRemoteMediator(
    private val movieAppDatabase: MovieAppDatabase,
    private val movieAppApi: MovieAppApi
) : RemoteMediator<Int, MovieEntity>() {

    companion object {
        private const val REMOTE_KEY_LABEL = "moviesPage"
    }

    override suspend fun load(loadType: LoadType, state: PagingState<Int, MovieEntity>): MediatorResult {
        return try {
            val currentPage = when (loadType) {
                LoadType.REFRESH -> {
                    1
                }

                LoadType.PREPEND -> {
                    return MediatorResult.Success(true)
                }

                LoadType.APPEND -> {
                    val lastRemoteKey = movieAppDatabase.remoteKeyDao.getRemoteKeyByLabel(REMOTE_KEY_LABEL)
                    lastRemoteKey?.nextKey ?: 1
                }
            }

            val response = movieAppApi.getMovies(page = currentPage)

            movieAppDatabase.withTransaction {
                if (loadType == LoadType.REFRESH) {
                    movieAppDatabase.movieDao.clearAll()
                    movieAppDatabase.remoteKeyDao.deleteRemoteKeyByLabel(REMOTE_KEY_LABEL)
                }
                val moviesEntities = response.results.map { it.toMovieEntity() }
                movieAppDatabase.movieDao.upsertMovies(movies = moviesEntities)
                movieAppDatabase.remoteKeyDao.upsertRemoteKey(
                    RemoteKeyEntity(
                        label = REMOTE_KEY_LABEL,
                        nextKey = response.page + 1
                    )
                )
            }

            val hasReachedEndOfPagination = response.page > 500 || response.results.isEmpty()
            MediatorResult.Success(endOfPaginationReached = hasReachedEndOfPagination)
        } catch (exc: Exception) {
            MediatorResult.Error(AppExceptionMapper.mapException(exc))
        }
    }
}