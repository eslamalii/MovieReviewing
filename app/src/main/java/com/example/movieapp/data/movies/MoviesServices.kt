package com.example.movieapp.data.movies

import com.example.movieapp.model.movies.MovieDetails
import com.example.movieapp.model.movies.popularMovies.PopularMovies
import com.example.movieapp.model.movies.upcomingMovies.UpcomingMovies
import com.example.movieapp.util.Constants
import hu.akarnokd.rxjava3.retrofit.RxJava3CallAdapterFactory
import io.reactivex.rxjava3.core.Observable
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


class MoviesServices {

    private val api: UpcomingMoviesApi = Retrofit.Builder()
        .baseUrl(Constants.BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
        .build()
        .create(UpcomingMoviesApi::class.java)

    fun getUpcomingMovies(): Observable<UpcomingMovies> {
        return api.getUpcomingMovies(Constants.API_ID, "en-US")
    }

    fun getPopularMovies(): Observable<PopularMovies> {
        return api.getPopularMovies(Constants.API_ID, "en-US")
    }

    fun getMovieDetails(id: Int): Observable<MovieDetails> {
        return api.getMovieDetails(id, Constants.API_ID, "en-US")
    }
}