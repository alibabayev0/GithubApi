package com.alibabayev.githubapi.data.datasource.remote

import com.alibabayev.githubapi.data.api.model.RepoResponse
import com.alibabayev.githubapi.data.api.model.UserResponse
import com.alibabayev.githubapi.data.api.GithubService
import retrofit2.Response
import javax.inject.Inject

class GithubRemoteDataSourceImpl @Inject constructor(
    private val githubService: GithubService
) : GithubRemoteDataSource {
    override suspend fun getUsers(): Response<List<UserResponse>> {
        return githubService.getUsers()
    }

    override suspend fun getUserRepos(login: String): Response<List<RepoResponse>> {
        return githubService.getUserRepos(login)
    }
}
