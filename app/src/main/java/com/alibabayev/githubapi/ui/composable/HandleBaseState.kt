package com.alibabayev.githubapi.ui.composable

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import com.alibabayev.githubapi.ui.base.BaseState
import com.alibabayev.githubapi.ui.base.BaseViewModel

@Composable
fun HandleBaseState(viewModel: BaseViewModel, handler: @Composable (BaseState) -> Unit) {
    val state by viewModel.state.collectAsState()

    handler(state)

    viewModel.resetState()
}
