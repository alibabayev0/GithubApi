package com.alibabayev.githubapi.data.datasource.local

import com.alibabayev.githubapi.data.database.entity.RepoEntity
import com.alibabayev.githubapi.data.database.entity.UserEntity

interface GithubLocalDataSource {
    suspend fun getUsers(): List<UserEntity>?
    suspend fun insertUsers(users: List<UserEntity>)

    suspend fun getUserRepos(login: String): List<RepoEntity>?
    suspend fun insertUserRepos(userRepos: List<RepoEntity>)
}
