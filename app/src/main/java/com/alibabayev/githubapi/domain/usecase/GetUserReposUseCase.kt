package com.alibabayev.githubapi.domain.usecase

import com.alibabayev.githubapi.domain.model.Repo
import com.alibabayev.githubapi.domain.repository.GithubRepository
import com.alibabayev.githubapi.domain.util.RequestState
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class GetUserReposUseCase @Inject constructor(
    private val githubRepository: GithubRepository
) {
    operator fun invoke(login: String): Flow<RequestState<List<Repo>>> = flow<RequestState<List<Repo>>> {
        emit(RequestState.loading())
        githubRepository.getUserRepos(login).fold(
            onSuccess = { emit(RequestState.success(it)) },
            onFailure = { emit(RequestState.fail(it)) }
        )
    }.flowOn(Dispatchers.IO)
}
