package com.alibabayev.githubapi.data.datasource.local

import com.alibabayev.githubapi.data.database.dao.RepoDao
import com.alibabayev.githubapi.data.database.dao.UserDao
import com.alibabayev.githubapi.data.database.entity.RepoEntity
import com.alibabayev.githubapi.data.database.entity.UserEntity
import javax.inject.Inject

class GithubLocalDataSourceImpl @Inject constructor(
    private val userDao: UserDao,
    private val repoDao: RepoDao
) : GithubLocalDataSource {
    override suspend fun getUsers(): List<UserEntity>? {
        return userDao.getAll()
    }

    override suspend fun insertUsers(users: List<UserEntity>) {
        return userDao.insertAll(users)
    }

    override suspend fun getUserRepos(login: String): List<RepoEntity>? {
        return repoDao.getAll(login)
    }

    override suspend fun insertUserRepos(userRepos: List<RepoEntity>) {
        return repoDao.insertAll(userRepos)
    }
}
