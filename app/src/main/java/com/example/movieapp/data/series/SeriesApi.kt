package com.example.movieapp.data.series

import com.example.movieapp.model.series.genres.Genres
import com.example.movieapp.model.series.latestSeries.LatestSeries
import com.example.movieapp.model.series.tvShows.TvShows
import io.reactivex.rxjava3.core.Observable
import retrofit2.http.GET
import retrofit2.http.Query

interface SeriesApi {

    @GET("tv/latest")
    fun getLatestSeries(
        @Query("api_key") api: String,
        @Query("language") language: String
    ): Observable<LatestSeries>

    @GET("discover/tv")
    fun getTvShows(
        @Query("api_key") api: String,
        @Query("language") language: String,
        @Query("sort_by") sort_by: String,
        @Query("timezone") timeZone: String,
    ) : Observable<TvShows>

}