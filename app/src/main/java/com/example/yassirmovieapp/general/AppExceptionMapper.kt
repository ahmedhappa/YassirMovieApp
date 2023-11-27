package com.example.yassirmovieapp.general

import android.accounts.NetworkErrorException
import com.example.yassirmovieapp.R
import com.google.gson.JsonParseException
import com.google.gson.stream.MalformedJsonException
import retrofit2.HttpException
import java.net.HttpURLConnection
import java.net.SocketException
import java.net.SocketTimeoutException
import java.net.UnknownHostException

object AppExceptionMapper {
    fun mapException(exc: Throwable): CustomAppException {
        return when (exc) {
            is HttpException -> {
                when {
                    exc.code() >= HttpURLConnection.HTTP_INTERNAL_ERROR ->
                        CustomAppException.ServerException(UiText.ResourceText(R.string.error_internal_server_error))

                    exc.code() == HttpURLConnection.HTTP_UNAUTHORIZED -> {
                        CustomAppException.UnAuthorizedException(UiText.ResourceText(R.string.error_unauthorized_access))
                    }

                    else ->
                        CustomAppException.UnKnownException(UiText.ResourceText(R.string.error_something_went_wrong_we_are_trying_to_fix_it))
                }
            }

            is NetworkErrorException, is SocketException, is UnknownHostException ->
                CustomAppException.NetworkException(UiText.ResourceText(R.string.error_enable_mobile_data_services))

            is SocketTimeoutException ->
                CustomAppException.NetworkException(UiText.ResourceText(R.string.error_request_timeout))

            is JsonParseException, is MalformedJsonException ->
                CustomAppException.ApiException(UiText.ResourceText(R.string.error_something_went_wrong_we_are_trying_to_fix_it))

            else ->
                CustomAppException.UnKnownException(UiText.ResourceText(R.string.error_something_went_wrong_please_try_again_later))
        }
    }
}