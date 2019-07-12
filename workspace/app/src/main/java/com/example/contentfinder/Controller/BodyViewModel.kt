package com.example.contentfinder.Controller0

import android.arch.lifecycle.*

class BodyViewModel : ViewModel() {

    private val _index = MutableLiveData<Int>()
    val text: LiveData<Int> = Transformations.map(_index) {
        it
    }

    fun setIndex(index: Int) {
        _index.value = index
    }
}