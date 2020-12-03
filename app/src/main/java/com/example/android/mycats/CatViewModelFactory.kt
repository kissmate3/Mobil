package com.example.android.mycats

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import java.lang.IllegalArgumentException

class CatViewModelFactory(private val repository: CatRepository):ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if(modelClass.isAssignableFrom(CatViewModel::class.java)){
            return CatViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown View Model class")
    }

}