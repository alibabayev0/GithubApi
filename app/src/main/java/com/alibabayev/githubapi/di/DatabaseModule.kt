package com.alibabayev.githubapi.di

import android.app.Application
import androidx.room.Room
import com.alibabayev.githubapi.data.database.GithubDatabase
import com.alibabayev.githubapi.data.database.dao.RepoDao
import com.alibabayev.githubapi.data.database.dao.UserDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

private const val DATABASE_NAME = "GithubDatabase"

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {
    @Provides
    @Singleton
    fun provideDb(application: Application): GithubDatabase {
        return Room.databaseBuilder(
            application,
            GithubDatabase::class.java,
            DATABASE_NAME
        ).fallbackToDestructiveMigration().build()
    }

    @Provides
    @Singleton
    fun provideUserDao(
        githubDb: GithubDatabase
    ): UserDao = githubDb.userDao()

    @Provides
    @Singleton
    fun provideRepoDao(
        githubDb: GithubDatabase
    ): RepoDao = githubDb.repoDao()
}
