package com.example.yassirmovieapp.domain.model

import java.text.NumberFormat
import java.util.Locale

data class Movie(
    val id: Int,
    val image: String?,
    val backgroundImage:String?,
    val title: String,
    val releaseDate: String?,
    val voteRating: Double,
    val status: String?,
    val overview: String?,
) {
    val fullImageUrl: String
        get() = "https://image.tmdb.org/t/p/w500$image"

    val backgroundImageFullUrl:String
        get() = "https://image.tmdb.org/t/p/original$backgroundImage"

    val formattedVoteRatting: String
        get() {
            val numberFormatter = NumberFormat.getNumberInstance(Locale.getDefault()).apply {
                minimumFractionDigits = 1
                maximumFractionDigits = 1
            }
            return try {
                numberFormatter.format(voteRating)
            } catch (exc: Exception) {
                exc.printStackTrace()
                voteRating.toString()
            }
        }
}
