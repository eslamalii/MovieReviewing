package com.example.movieapp.data.series

import com.example.movieapp.model.series.latestSeries.SeriesModel
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


    fun getLatestSeries(): Observable<SeriesModel> {
        return api.getLatestSeries(Constants.API_ID, "en-US")
    }

    fun getTvShows(): Observable<TvShows> {
        return api.getTvShows(Constants.API_ID, "en_US", "popularity.desc", "America/New_York")
    }

    fun getTvDetails(id: Int): Observable<SeriesModel> {
        return api.getSeriesDetails(id, Constants.API_ID, "en_US")
    }
}