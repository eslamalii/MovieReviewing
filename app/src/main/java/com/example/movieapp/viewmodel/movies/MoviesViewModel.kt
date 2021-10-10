package com.example.movieapp.viewmodel.movies

import android.util.Log
import android.view.View
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.movieapp.data.movies.UpcomingService
import com.example.movieapp.model.movies.popularMovies.PopularMovies
import com.example.movieapp.model.movies.upcomingMovies.UpcomingMovies
import com.example.movieapp.model.series.genres.Genres
import com.example.movieapp.model.series.genres.GenresValues
import com.example.movieapp.util.Genre
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.schedulers.Schedulers

class MoviesViewModel : ViewModel() {
    private val upcomingMoviesService = UpcomingService()
    val upcomingMoviesList = MutableLiveData<UpcomingMovies?>()
    val upComingProgressBar = MutableLiveData<Int>()
    val popularList = MutableLiveData<PopularMovies>()
    val genres = MutableLiveData<List<GenresValues>>()

    init {
        fetchPopularMovies()
        fetchUpcomingMovies()
        fetchGenres()
    }
    private fun fetchUpcomingMovies() {
        upComingProgressBar.value = View.GONE
        val upcomingMovies: Observable<UpcomingMovies> = upcomingMoviesService.getUpcomingMovies()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())

        upcomingMovies.subscribe(
            {
                upcomingMoviesList.value = it
                upComingProgressBar.value = 0
            }, {
                Log.i("TAG", "fetchUpcomingMovies: $it")
            }
        )
    }

    private fun fetchPopularMovies() {
        val popularMovies: Observable<PopularMovies> = upcomingMoviesService.getPopularMovies()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())

        popularMovies.subscribe(
            {
                popularList.value = it
            }, {
                Log.d("TAG", "fetchPopularMovies: $it ")
            }
        )
    }

    private fun fetchGenres(){
        genres.value = Genre.getAllGenre()
    }
}