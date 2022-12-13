package com.alibabayev.githubapi.data.api

import com.alibabayev.githubapi.data.api.model.RepoResponse
import com.alibabayev.githubapi.data.api.model.UserResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface GithubService {
    @GET("users")
    suspend fun getUsers(): Response<List<UserResponse>>

    @GET("users/{login}/repos")
    suspend fun getUserRepos(@Path("login") login: String): Response<List<RepoResponse>>
}
