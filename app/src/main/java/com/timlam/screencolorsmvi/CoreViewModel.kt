package com.timlam.screencolorsmvi

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch

@ExperimentalCoroutinesApi
class CoreViewModel constructor(
    private val repository: CoreRepository,
    initialState: CoreContract.State = CoreContract.State()
) : ViewModel() {

    private val state: MutableStateFlow<CoreContract.State> = MutableStateFlow(initialState)
    private val effect = Channel<CoreContract.Effect>()

    val currentState: CoreContract.State
        get() = state.value

    fun state(): StateFlow<CoreContract.State> = state

    fun effect(): Flow<CoreContract.Effect> = effect.receiveAsFlow()

    fun onEvent(event: CoreContract.Event) = viewModelScope.launch {
        handleEvent(event)
    }

    suspend fun handleEvent(event: CoreContract.Event) {
        when (event) {
            is CoreContract.Event.OnChangeColorClicked -> setState { copy(color = repository.getColor(currentState.color)) }
        }
    }

    private fun setState(reduce: CoreContract.State.() -> CoreContract.State) {
        val newState = currentState.reduce()
        state.value = newState
    }

    fun setEffect(builder: () -> CoreContract.Effect) {
        val effectValue = builder()
        viewModelScope.launch { effect.send(effectValue) }
    }

}
