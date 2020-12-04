package com.timlam.screencolorsmvi.presentation.colors

import androidx.hilt.lifecycle.ViewModelInject
import com.timlam.domain.interactors.GetColorUseCase
import com.timlam.screencolorsmvi.R
import com.timlam.screencolorsmvi.framework.CoreViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi

@ExperimentalCoroutinesApi
class ColorsViewModel @ViewModelInject constructor(
    private val getColor: GetColorUseCase
) : CoreViewModel<ColorsContract.Event, ColorsContract.State, ColorsContract.Effect>(ColorsContract.State()) {

    override suspend fun handleEvent(event: ColorsContract.Event) {
        val newColor = getColor(currentState.colorNumber)
        when (event) {
            is ColorsContract.Event.OnChangeColorClicked -> setState {
                copy(colorNumber = newColor)
            }
        }
    }

    fun backgroundClicked() {
        setEffect { ColorsContract.Effect.ShowColorsToast }
    }

    fun getColorFromColorNumber(colorNumber: Int): Int {
        return when (colorNumber) {
            0 -> R.color.purple_200
            1 -> R.color.purple_500
            2 -> R.color.purple_700
            3 -> R.color.teal_200
            4 -> R.color.teal_700
            5 -> R.color.black
            else -> R.color.white
        }
    }

}
