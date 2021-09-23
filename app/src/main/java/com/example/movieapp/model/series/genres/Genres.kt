package com.example.movieapp.model.series.genres

import android.telecom.Call
import com.google.gson.annotations.SerializedName

data class Genres(
    @SerializedName("genres") var genres: List<GenresValues>

)

