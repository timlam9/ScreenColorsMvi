package com.timlam.screencolorsmvi.presentation.colors

import com.timlam.screencolorsmvi.framework.UiEffect
import com.timlam.screencolorsmvi.framework.UiEvent
import com.timlam.screencolorsmvi.framework.UiState

class ColorsContract {

    sealed class Event : UiEvent {

        object OnChangeColorClicked : Event()

    }

    data class State(
        val isLoading: Boolean = false,
        val colorNumber: Int = 0,
        val error: String? = null
    ) : UiState

    sealed class Effect : UiEffect {

        object ShowColorsToast : Effect()

    }

}
