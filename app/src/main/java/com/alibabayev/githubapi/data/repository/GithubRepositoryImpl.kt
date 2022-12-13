package com.alibabayev.githubapi.data.repository

import com.alibabayev.githubapi.data.api.model.toDomain
import com.alibabayev.githubapi.data.api.model.toEntity
import com.alibabayev.githubapi.data.database.entity.toDomain
import com.alibabayev.githubapi.data.datasource.local.GithubLocalDataSource
import com.alibabayev.githubapi.data.datasource.remote.GithubRemoteDataSource
import com.alibabayev.githubapi.domain.model.Repo
import com.alibabayev.githubapi.domain.model.User
import com.alibabayev.githubapi.domain.repository.GithubRepository
import javax.inject.Inject

// Methods could be excluded abstract class, which we could use saveDataLocal, fetchRemote and fetchLocal data abstract methods, but
// I prefer to repeat instead of DRY principle. Because, if we use it with template class, it will be hard to change anything during data transfer.
class GithubRepositoryImpl @Inject constructor(
    private val githubLocalDataSource: GithubLocalDataSource,
    private val githubRemoteDataSource: GithubRemoteDataSource
) : GithubRepository {
    override suspend fun getUsers(): Result<List<User>> = runCatching {
        val dbResponse = githubLocalDataSource.getUsers()
        val apiResponse = githubRemoteDataSource.getUsers()

        if (apiResponse.isSuccessful) apiResponse.body()
            ?.also { data -> githubLocalDataSource.insertUsers(data.map { it.toEntity() }) }

        if (!dbResponse.isNullOrEmpty()) dbResponse.map { it.toDomain() }
        else apiResponse.body()?.map { it.toDomain() } ?: listOf()
    }

    override suspend fun getUserRepos(login: String): Result<List<Repo>> = runCatching {
        val dbResponse = githubLocalDataSource.getUserRepos(login)
        val apiResponse = githubRemoteDataSource.getUserRepos(login)

        if (apiResponse.isSuccessful) apiResponse.body()
            ?.also { data -> githubLocalDataSource.insertUserRepos(data.map { it.toEntity() }) }

        if (!dbResponse.isNullOrEmpty()) dbResponse.map { it.toDomain() }
        else apiResponse.body()?.map { it.toDomain() } ?: listOf()
    }
}
