package com.example.yassirmovieapp.general

sealed class Resource<out T> {
    class Loading<out T>(val data: T? = null) : Resource<T>()
    class Success<out T>(val data: T, val message: UiText? = null) : Resource<T>()
    class Error<out T>(val customAppException: CustomAppException, val data: T? = null) : Resource<T>()
}
