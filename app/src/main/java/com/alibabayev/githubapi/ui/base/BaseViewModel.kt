package com.alibabayev.githubapi.ui.base

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

abstract class BaseViewModel : ViewModel() {
    private val _state = MutableStateFlow<BaseState>(UninitializedState)
    val state: StateFlow<BaseState>
        get() = _state

    fun resetState() {
        _state.value = IdleState
    }
}
