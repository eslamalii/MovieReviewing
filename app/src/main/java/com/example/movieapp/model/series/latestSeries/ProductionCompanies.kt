package com.example.movieapp.model.series.latestSeries

import com.google.gson.annotations.SerializedName

class ProductionCompanies(

    @SerializedName("id") var id: Int,
    @SerializedName("logo_path") var logoPath: String,
    @SerializedName("name") var name: String,
    @SerializedName("origin_country") var originCountry: String

)
