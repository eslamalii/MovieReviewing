package com.example.movieapp.data.movies

import com.example.movieapp.model.movies.MovieDetails
import com.example.movieapp.model.movies.popularMovies.PopularMovies
import com.example.movieapp.model.movies.upcomingMovies.UpcomingMovies
import io.reactivex.rxjava3.core.Observable
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface UpcomingMoviesApi {

    @GET("movie/upcoming")
    fun getUpcomingMovies(
        @Query("api_key") api: String,
        @Query("language") language: String
    ): Observable<UpcomingMovies>

    @GET("movie/popular")
    fun getPopularMovies(
        @Query("api_key") api: String,
        @Query("language") language: String
    ): Observable<PopularMovies>

    @GET("movie/{movie_id}")
    fun getMovieDetails(
        @Path("movie_id") id: Int,
        @Query("api_key") api: String,
        @Query("language") language: String
    ): Observable<MovieDetails>

}