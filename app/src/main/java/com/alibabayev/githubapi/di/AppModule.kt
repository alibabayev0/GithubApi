package com.alibabayev.githubapi.di

import android.content.Context
import com.alibabayev.githubapi.App
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import java.io.File
import java.util.*
import javax.inject.Named
import javax.inject.Singleton

const val CACHE_DIR = "CACHE_DIR"

@Module
@InstallIn(SingletonComponent::class)
class AppModule {
    @Singleton
    @Provides
    fun provideApplication(@ApplicationContext app: Context): App {
        return app as App
    }

    @Singleton
    @Provides
    @Named(CACHE_DIR)
    fun provideCacheDir(@ApplicationContext app: Context): File {
        return app.cacheDir
    }
}
