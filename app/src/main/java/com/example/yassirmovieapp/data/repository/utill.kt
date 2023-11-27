package com.example.yassirmovieapp.data.repository

import com.example.yassirmovieapp.general.AppExceptionMapper
import com.example.yassirmovieapp.general.Resource
import com.example.yassirmovieapp.general.UiText
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn

inline fun <R, T> cachedDataWithApiBackup(
    crossinline loadFromCache: suspend () -> T,
    crossinline networkCall: suspend () -> R,
    crossinline cacheOperation: suspend (R) -> Unit,
    crossinline successMessageProvider: ((R) -> UiText?) = { null }
): Flow<Resource<T>> = flow {
    emit(Resource.Loading())
    emit(Resource.Loading(loadFromCache()))
    val response = networkCall()
    cacheOperation(response)
    emit(
        Resource.Success(
            data = loadFromCache(),
            message = successMessageProvider.invoke(response)
        )
    )
}.catch {
    it.printStackTrace()
    val callException = AppExceptionMapper.mapException(it)
    emit(Resource.Error(callException))
}.flowOn(Dispatchers.IO)