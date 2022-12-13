package com.alibabayev.githubapi.di

import com.alibabayev.githubapi.data.datasource.local.GithubLocalDataSource
import com.alibabayev.githubapi.data.datasource.remote.GithubRemoteDataSource
import com.alibabayev.githubapi.data.repository.GithubRepositoryImpl
import com.alibabayev.githubapi.domain.repository.GithubRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
object RepositoryModule {
    @Singleton
    @Provides
    fun provideGithubRepository(
        localDataSource: GithubLocalDataSource,
        remoteDataSource: GithubRemoteDataSource
    ): GithubRepository {
        return GithubRepositoryImpl(localDataSource, remoteDataSource)
    }
}
