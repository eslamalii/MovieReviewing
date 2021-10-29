package com.example.movieapp.viewmodel

import androidx.lifecycle.ViewModel
import com.example.movieapp.util.SingleLiveEvent

open class BaseViewModel : ViewModel() {

    protected val processBar: SingleLiveEvent<Boolean> = SingleLiveEvent()
}