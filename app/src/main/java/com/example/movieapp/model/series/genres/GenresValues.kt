package com.example.movieapp.model.series.genres

import com.google.gson.annotations.SerializedName

data class GenresValues(

    @SerializedName("id") var id: Int,
    @SerializedName("name") var name: String

)