package com.example.yassirmovieapp.general

class Event<T>(private val value: T) {
    private var isContentHandled = false

    fun getContentIfNotHandled(): T? {
        if (isContentHandled) {
            return null
        }

        isContentHandled = true
        return value
    }
}