package com.alibabayev.githubapi.domain.repository

import com.alibabayev.githubapi.domain.model.Repo
import com.alibabayev.githubapi.domain.model.User

interface GithubRepository {
    suspend fun getUsers(): Result<List<User>>

    suspend fun getUserRepos(login: String): Result<List<Repo>>
}
