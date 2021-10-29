package com.example.movieapp.data.series

import com.example.movieapp.model.series.latestSeries.SeriesModel
import com.example.movieapp.model.series.tvShows.TvShows
import io.reactivex.rxjava3.core.Observable
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface SeriesApi {

    @GET("tv/latest")
    fun getLatestSeries(
        @Query("api_key") api: String,
        @Query("language") language: String
    ): Observable<SeriesModel>

    @GET("discover/tv")
    fun getTvShows(
        @Query("api_key") api: String,
        @Query("language") language: String,
        @Query("sort_by") sort_by: String,
        @Query("timezone") timeZone: String,
    ): Observable<TvShows>

    @GET("tv/{tv_id}")
    fun getSeriesDetails(
        @Path("tv_id") id: Int,
        @Query("api_key") api: String,
        @Query("language") language: String
    ): Observable<SeriesModel>

}