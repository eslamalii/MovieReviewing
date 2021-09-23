package com.example.movieapp.data.movies

import com.example.movieapp.model.movies.upcomingMovies.UpcomingMovies
import com.example.movieapp.model.series.genres.Genres
import io.reactivex.rxjava3.core.Observable
import retrofit2.http.GET
import retrofit2.http.Query

interface UpcomingMoviesApi {

    @GET("movie/upcoming")
    fun getUpcomingMovies(
        @Query("api_key") api: String,
        @Query("language") language: String
    ): Observable<UpcomingMovies>

    @GET("genre/movie/list")
    fun getGenresList(
        @Query("api_key") api: String,
        @Query("language") language: String
    ): Observable<Genres>
}