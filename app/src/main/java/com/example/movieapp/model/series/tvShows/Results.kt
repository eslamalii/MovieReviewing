package com.example.movieapp.model.series.tvShows

import com.example.movieapp.util.Constants
import com.google.gson.annotations.SerializedName

data class Results(
    @SerializedName("backdrop_path") var backdropPath: String,
    @SerializedName("first_air_date") var firstAirDate: String,
    @SerializedName("genre_ids") var genreIds: List<Int>,
    @SerializedName("id") var id: Int,
    @SerializedName("name") var name: String,
    @SerializedName("origin_country") var originCountry: List<String>,
    @SerializedName("original_language") var originalLanguage: String,
    @SerializedName("original_name") var originalName: String,
    @SerializedName("overview") var overview: String,
    @SerializedName("popularity") var popularity: Double,
    @SerializedName("poster_path") var posterPath: String,
    @SerializedName("vote_average") var voteAverage: Double,
    @SerializedName("vote_count") var voteCount: Int
) {
    fun getImageUrl(): String {
        return Constants.IMAGE_URL + posterPath
    }

}
