package com.alibabayev.githubapi.data.api.model

import com.alibabayev.githubapi.data.database.entity.UserEntity
import com.alibabayev.githubapi.domain.model.User
import com.google.gson.annotations.SerializedName

data class UserResponse(
    @SerializedName("login") var login: String? = null,
    @SerializedName("avatar_url") var avatarUrl: String? = null
)

internal fun UserResponse.toDomain(): User {
    return User(
        login = login ?: "",
        avatarUrl = avatarUrl
    )
}

internal fun UserResponse.toEntity(): UserEntity {
    return UserEntity(
        login = login ?: "",
        avatarUrl = avatarUrl
    )
}
