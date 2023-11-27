package com.example.yassirmovieapp.general

import android.content.Context
import androidx.annotation.StringRes

sealed class UiText {
    class DynamicText(val text: String) : UiText()
    class ResourceText(@StringRes val stringResourceId: Int, vararg val formatArgs: Any) : UiText()

    fun asString(context: Context): String {
        return when (this) {
            is DynamicText -> text

            is ResourceText -> context.getString(stringResourceId, *formatArgs)
        }
    }
}
