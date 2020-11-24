package com.timlam.screencolorsmvi.presentation.colors

import com.timlam.screencolorsmvi.R
import com.timlam.screencolorsmvi.framework.CoreViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi

@ExperimentalCoroutinesApi
class ColorsViewModel constructor(
    private val repository: ColorsRepository,
    initialState: ColorsContract.State = ColorsContract.State()
) : CoreViewModel<ColorsContract.Event, ColorsContract.State, ColorsContract.Effect>(initialState) {

    override suspend fun handleEvent(event: ColorsContract.Event) {
        when (event) {
            is ColorsContract.Event.OnChangeColorClicked -> setState {
                copy(colorNumber = repository.getColor(currentState.colorNumber))
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
