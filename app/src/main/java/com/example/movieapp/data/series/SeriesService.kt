package com.example.movieapp.data.series

import com.example.movieapp.model.series.genres.Genres
import com.example.movieapp.model.series.latestSeries.LatestSeries
import com.example.movieapp.model.series.tvShows.TvShows
import com.example.movieapp.util.Constants
import hu.akarnokd.rxjava3.retrofit.RxJava3CallAdapterFactory
import io.reactivex.rxjava3.core.Observable
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


class SeriesService {

    private var api: SeriesApi = Retrofit
        .Builder()
        .baseUrl(Constants.BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
        .build()
        .create(SeriesApi::class.java)


    fun getLatestSeries(): Observable<LatestSeries> {
        return api.getLatestSeries(Constants.API_ID, "en-US")
    }

    fun getGenresSeries(): Observable<Genres> {
        return api.getSeriesGenres(Constants.API_ID, "en_US")
    }

    fun getTvShows(): Observable<TvShows> {
        return api.getTvShows(Constants.API_ID, "en_US", "popularity.desc", "America/New_York")
    }
}