package com.timlam.screencolorsmvi

class CoreContract {

    sealed class Event {

        object OnChangeColorClicked : Event()

    }

    data class State(
        val isLoading: Boolean = false,
        val color: Int = 0,
        val error: String? = null
    )

    sealed class Effect {

        data class ShowToast(val message: String) : Effect()

    }

}
