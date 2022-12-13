package com.alibabayev.githubapi.di

import com.alibabayev.githubapi.data.api.GithubService
import com.alibabayev.githubapi.data.database.dao.RepoDao
import com.alibabayev.githubapi.data.database.dao.UserDao
import com.alibabayev.githubapi.data.datasource.local.GithubLocalDataSource
import com.alibabayev.githubapi.data.datasource.local.GithubLocalDataSourceImpl
import com.alibabayev.githubapi.data.datasource.remote.GithubRemoteDataSource
import com.alibabayev.githubapi.data.datasource.remote.GithubRemoteDataSourceImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
object DataSourceModule {
    @Singleton
    @Provides
    fun provideGithubRemoteDataSource(
        githubService: GithubService
    ): GithubRemoteDataSource {
        return GithubRemoteDataSourceImpl(githubService)
    }

    @Singleton
    @Provides
    fun provideGithubLocalDataSource(
        userDao: UserDao,
        repoDao: RepoDao
    ): GithubLocalDataSource {
        return GithubLocalDataSourceImpl(userDao, repoDao)
    }
}
