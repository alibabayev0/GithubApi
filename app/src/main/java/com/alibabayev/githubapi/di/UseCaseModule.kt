package com.alibabayev.githubapi.di

import com.alibabayev.githubapi.domain.repository.GithubRepository
import com.alibabayev.githubapi.domain.usecase.GetUserReposUseCase
import com.alibabayev.githubapi.domain.usecase.GetUsersUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
object UseCaseModule {
    @Singleton
    @Provides
    fun provideGetUsersUseCase(
        repository: GithubRepository
    ): GetUsersUseCase {
        return GetUsersUseCase(repository)
    }

    @Singleton
    @Provides
    fun provideGetUserReposUseCase(
        repository: GithubRepository
    ): GetUserReposUseCase {
        return GetUserReposUseCase(repository)
    }
}
