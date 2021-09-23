package com.example.movieapp.model.movies.upcomingMovies

import com.google.gson.annotations.SerializedName

data class UpcomingMovies(

    @SerializedName("dates") var dates: Dates,
    @SerializedName("page") var page: Int,
    @SerializedName("results") var results: List<Results>,
    @SerializedName("total_pages") var totalPages: Int,
    @SerializedName("total_results") var totalResults: Int

)
