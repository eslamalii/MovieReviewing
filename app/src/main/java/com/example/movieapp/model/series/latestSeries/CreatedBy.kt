package com.example.movieapp.model.series.latestSeries

import com.google.gson.annotations.SerializedName

data class CreatedBy(
    @SerializedName("id") var id : Int,
    @SerializedName("credit_id") var creditId : String,
    @SerializedName("name") var name : String,
    @SerializedName("gender") var gender : Int,
    @SerializedName("profile_path") var profilePath : String
)
