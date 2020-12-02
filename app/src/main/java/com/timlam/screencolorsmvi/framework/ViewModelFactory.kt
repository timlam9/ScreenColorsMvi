package com.timlam.screencolorsmvi.framework

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.timlam.data.ColorsRepository
import com.timlam.screencolorsmvi.presentation.colors.ColorsViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi

@Suppress("UNCHECKED_CAST")
@ExperimentalCoroutinesApi
class ViewModelFactory(private val repository: ColorsRepository) : ViewModelProvider.NewInstanceFactory() {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return ColorsViewModel(repository) as T
    }

}
