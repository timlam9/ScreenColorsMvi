package com.timlam.screencolorsmvi.framework

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import timber.log.Timber

@ExperimentalCoroutinesApi
abstract class CoreViewModel<E : UiEvent, S : UiState, F : UiEffect>(
    initialState: S
) : ViewModel() {

    private val state: MutableStateFlow<S> = MutableStateFlow(initialState)

    private val effect = Channel<F>()

    val currentState: S
        get() = state.value

    fun state(): StateFlow<S> = state

    fun effect(): Flow<F> = effect.receiveAsFlow()

    fun onEvent(event: E) = viewModelScope.launch {
        Timber.v(event.toString())
        handleEvent(event)
    }

    protected abstract suspend fun handleEvent(event: E)

    protected fun setState(reduce: S.() -> S) {
        val newState = currentState.reduce()
        Timber.v(newState.toString())
        state.value = newState
    }

    protected fun setEffect(builder: () -> F) {
        val effectValue = builder()
        Timber.v(effectValue.toString())
        viewModelScope.launch { effect.send(effectValue) }
    }

}
