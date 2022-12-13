package com.alibabayev.githubapi.data.datasource.remote

import com.alibabayev.githubapi.data.api.model.RepoResponse
import com.alibabayev.githubapi.data.api.model.UserResponse
import retrofit2.Response

interface GithubRemoteDataSource {
    suspend fun getUsers(): Response<List<UserResponse>>
    suspend fun getUserRepos(login: String): Response<List<RepoResponse>>
}