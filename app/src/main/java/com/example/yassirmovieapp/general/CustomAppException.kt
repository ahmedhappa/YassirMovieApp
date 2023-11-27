package com.example.yassirmovieapp.general

sealed class CustomAppException(val uiText: UiText) : Exception() {
    class ServerException(serverException: UiText) : CustomAppException(serverException)
    class UnAuthorizedException(unAuthException: UiText) : CustomAppException(unAuthException)
    class ApiException(apiException: UiText) : CustomAppException(apiException)
    class NetworkException(networkException: UiText) : CustomAppException(networkException)
    class UnKnownException(unknownException: UiText) : CustomAppException(unknownException)
}
