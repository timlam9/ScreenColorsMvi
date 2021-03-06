package com.timlam.screencolorsmvi.presentation.colors

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.viewModelScope
import com.timlam.domain.interactors.GetColorUseCase
import com.timlam.screencolorsmvi.R
import com.timlam.screencolorsmvi.firebase.FirestoreDataSource
import com.timlam.screencolorsmvi.framework.CoreViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.launch

@ExperimentalCoroutinesApi
class ColorsViewModel @ViewModelInject constructor(
    private val getAwesomeColor: GetColorUseCase,
    private val firestoreDataSource: FirestoreDataSource
) : CoreViewModel<ColorsContract.Event, ColorsContract.State, ColorsContract.Effect>(ColorsContract.State()) {

    override suspend fun handleEvent(event: ColorsContract.Event) {
        when (event) {
            is ColorsContract.Event.OnChangeColorClicked -> changeFirestoreColorState()
            is ColorsContract.Event.OnBackgroundClicked -> backgroundClicked()
        }
    }

    private suspend fun changeColorState() {
        val newAwesomeColor = getAwesomeColor(currentState.colorNumber)
        setState {
            copy(colorNumber = newAwesomeColor.color)
        }
    }

    private fun backgroundClicked() {
        setEffect { ColorsContract.Effect.ShowColorsToast }
    }

    fun getColorFromColorNumber(colorNumber: Int): Int {
        return when (colorNumber) {
            0 -> R.color.purple_200
            1 -> R.color.purple_500
            2 -> R.color.purple_700
            3 -> R.color.teal_200
            4 -> R.color.teal_700
            5 -> R.color.red_500
            6 -> R.color.blue_500
            7 -> R.color.green_500
            8 -> R.color.yellow_500
            9 -> R.color.orange_500
            10 -> R.color.black
            else -> R.color.white
        }
    }

    private fun changeFirestoreColorState() {
        viewModelScope.launch {
            setState { copy(isLoading = true) }
            try {
                val newColor = firestoreDataSource.changeColor(state().value.colorNumber) ?: 0
                setState {
                    copy(isLoading = false, colorNumber = newColor)
                }
            } catch (ex: Exception) {
                setState {
                    copy(isLoading = false, error = ex.message)
                }
            }
        }
    }

}
