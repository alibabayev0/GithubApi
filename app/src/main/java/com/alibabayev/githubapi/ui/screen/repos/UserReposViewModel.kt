package com.alibabayev.githubapi.ui.screen.repos

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.alibabayev.githubapi.domain.model.Repo
import com.alibabayev.githubapi.domain.usecase.GetUserReposUseCase
import com.alibabayev.githubapi.ui.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class UserReposViewModel @Inject constructor(
    private val getUserReposUseCase: GetUserReposUseCase
) : BaseViewModel() {
    private val _userRepoList = MutableStateFlow<List<Repo>?>(null)
    val userRepoList = _userRepoList.asStateFlow()

    fun fetchRepos(login: String) {
        viewModelScope.launch {
            getUserReposUseCase.invoke(login).collect { it ->
                it.data?.also { _userRepoList.value = it }
            }
        }
    }
}
