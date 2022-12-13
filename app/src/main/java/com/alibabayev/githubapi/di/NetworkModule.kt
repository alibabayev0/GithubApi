package com.alibabayev.githubapi.di

import com.alibabayev.githubapi.BuildConfig
import com.alibabayev.githubapi.data.api.GithubService
import com.alibabayev.githubapi.data.api.deserializer.JsonDateDeserializer
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.Cache
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.io.File
import java.util.*
import java.util.concurrent.TimeUnit
import javax.inject.Named
import javax.inject.Singleton

private const val LOGGING_INTERCEPTOR = "LOGGING_INTERCEPTOR"
private const val TIMEOUT = 60L // 1 Minute Timeout for reading and writing
private const val DIR_CACHE = "http_cache" // 100 MB Cache
private const val MAX_SIZE_CACHE = 100L * 1024 * 1024 // 100 MB Cache

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {
    @Singleton
    @Provides
    fun provideGsonBuilder(): Gson {
        return GsonBuilder()
            .registerTypeAdapter(Date::class.java, JsonDateDeserializer())
            .create()
    }

    @Singleton
    @Provides
    fun provideRetrofit(gson: Gson): Retrofit.Builder {
        return Retrofit.Builder()
            .baseUrl(BuildConfig.API_URL)
            .addConverterFactory(GsonConverterFactory.create(gson))
    }

    @Singleton
    @Provides
    @Named(LOGGING_INTERCEPTOR)
    fun provideHttpLogging(): HttpLoggingInterceptor {
        return HttpLoggingInterceptor().apply {
            level =
                if (BuildConfig.DEBUG) HttpLoggingInterceptor.Level.BODY else HttpLoggingInterceptor.Level.NONE
        }
    }

    @Singleton
    @Provides
    fun provideCache(@Named(CACHE_DIR) cacheDir: File): Cache {
        val cacheDirPath = File(cacheDir, DIR_CACHE)
        return Cache(cacheDirPath, MAX_SIZE_CACHE)
    }

    @Singleton
    @Provides
    fun provideHttpClient(
        @Named(LOGGING_INTERCEPTOR) loggingInterceptor: HttpLoggingInterceptor,
        cache: Cache
    ): OkHttpClient {
        return OkHttpClient.Builder()
            .addInterceptor(loggingInterceptor)
            .connectTimeout(TIMEOUT, TimeUnit.SECONDS)
            .readTimeout(TIMEOUT, TimeUnit.SECONDS)
            .cache(cache)
            .build()
    }

    @Singleton
    @Provides
    fun provideGithubService(
        httpClient: OkHttpClient,
        retrofit: Retrofit.Builder
    ): GithubService {
        return retrofit
            .client(httpClient)
            .build()
            .create(GithubService::class.java)
    }
}
