package com.timlam.screencolorsmvi

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import kotlinx.coroutines.ExperimentalCoroutinesApi

@Suppress("UNCHECKED_CAST")
@ExperimentalCoroutinesApi
class ViewModelFactory(private val repository: CoreRepository) : ViewModelProvider.NewInstanceFactory() {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return CoreViewModel(repository) as T
    }

}
