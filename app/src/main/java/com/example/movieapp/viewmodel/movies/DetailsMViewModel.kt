package com.example.movieapp.viewmodel.movies

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.movieapp.data.movies.MoviesServices
import com.example.movieapp.model.movies.MovieDetails
import com.example.movieapp.viewmodel.BaseViewModel
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.schedulers.Schedulers

class DetailsMViewModel : BaseViewModel() {


    //Movies Details
    private val moviesServices = MoviesServices()

    private val moviesDetails: MutableLiveData<MovieDetails> = MutableLiveData()

    fun fetchMovieDetails(id: Int) {
        processBar.value = true
        val movies: Observable<MovieDetails> = moviesServices.getMovieDetails(id)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())

        movies.subscribe({
            moviesDetails.postValue(it)
        }, {
            Log.d("TAG", "fetchMovieDetails: $it")
        })
        processBar.value = false
    }

    fun getDetails(): LiveData<MovieDetails> = moviesDetails
    fun getLoading(): LiveData<Boolean> = processBar

}

