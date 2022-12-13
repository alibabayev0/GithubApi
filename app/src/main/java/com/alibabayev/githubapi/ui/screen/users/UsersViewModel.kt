package com.alibabayev.githubapi.ui.screen.users

import androidx.lifecycle.viewModelScope
import com.alibabayev.githubapi.domain.model.User
import com.alibabayev.githubapi.domain.usecase.GetUsersUseCase
import com.alibabayev.githubapi.ui.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class UsersViewModel @Inject constructor(
    private val getUsersUseCase: GetUsersUseCase
) : BaseViewModel() {
    private val _userList = MutableStateFlow<List<User>?>(null)
    val userList = _userList.asStateFlow()

    fun fetchUsers() {
        viewModelScope.launch {
            getUsersUseCase.invoke().collect { it ->
                it.data?.also { _userList.value = it }
            }
        }
    }
}
