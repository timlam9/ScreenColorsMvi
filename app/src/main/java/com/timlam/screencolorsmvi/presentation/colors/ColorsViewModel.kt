package com.timlam.screencolorsmvi.presentation.colors

import com.timlam.screencolorsmvi.framework.CoreViewModel
import com.timlam.screencolorsmvi.presentation.colors.ColorsContract
import com.timlam.screencolorsmvi.presentation.colors.ColorsRepository
import kotlinx.coroutines.ExperimentalCoroutinesApi

@ExperimentalCoroutinesApi
class ColorsViewModel constructor(
    private val repository: ColorsRepository,
    initialState: ColorsContract.State = ColorsContract.State()
) : CoreViewModel<ColorsContract.Event, ColorsContract.State, ColorsContract.Effect>(initialState) {

    override suspend fun handleEvent(event: ColorsContract.Event) {
        when (event) {
            is ColorsContract.Event.OnChangeColorClicked -> setState {
                copy(color = repository.getColor(currentState.color))
            }
        }
    }

    //TODO: Left this just to compare the 2 solutions. After i can remove it
    fun showToast(effect: ColorsContract.Effect) {
        setEffect { effect }
    }

    fun backgroundClicked() {
        setEffect { ColorsContract.Effect.ShowColorsToast }
    }

}
