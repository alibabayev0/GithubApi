package com.alibabayev.githubapi.domain.util

sealed class RequestState<T>(val data: T?, val error: Throwable?) {
    companion object {
        fun <T> idle(): RequestState<T> = Idle()
        fun <T> loading(): RequestState<T> = Loading()
        fun <T> success(data: T): RequestState<T> = Success(data)
        fun <T> fail(error: Throwable): RequestState<T> = Fail(error)
    }

    val hasData: Boolean
        get() = this is Success && data != null
}

class Idle<T> : RequestState<T>(null, null)

class Loading<T> : RequestState<T>(null, null)

class Success<T>(data: T) : RequestState<T>(data, null)

class Fail<T>(error: Throwable) : RequestState<T>(null, error)
