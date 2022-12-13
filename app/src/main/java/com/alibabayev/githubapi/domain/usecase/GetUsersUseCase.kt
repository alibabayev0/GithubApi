package com.alibabayev.githubapi.domain.usecase

import com.alibabayev.githubapi.domain.model.User
import com.alibabayev.githubapi.domain.repository.GithubRepository
import com.alibabayev.githubapi.domain.util.RequestState
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class GetUsersUseCase @Inject constructor(
    private val githubRepository: GithubRepository
) {
    operator fun invoke(): Flow<RequestState<List<User>>> = flow<RequestState<List<User>>> {
        emit(RequestState.loading())
        githubRepository.getUsers().fold(
            onSuccess = { emit(RequestState.success(it)) },
            onFailure = { emit(RequestState.fail(it)) }
        )
    }.flowOn(Dispatchers.IO)
}
