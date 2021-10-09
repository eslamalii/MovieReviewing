package com.example.movieapp.model.movies.popularMovies

import com.google.gson.annotations.SerializedName

data class PopularMovies(
    @SerializedName("page") var page : Int,
    @SerializedName("results") var results : List<Results>,
    @SerializedName("total_pages") var totalPages : Int,
    @SerializedName("total_results") var totalResults : Int
)
