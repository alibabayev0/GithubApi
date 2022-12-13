package com.alibabayev.githubapi.ui.base

interface BaseState

object UninitializedState : BaseState
object IdleState : BaseState
// data class ErrorState(val error: Throwable?) : BaseState
