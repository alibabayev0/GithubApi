package com.alibabayev.githubapi.data.database.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.alibabayev.githubapi.domain.model.User

@Entity(tableName = "users")
data class UserEntity(
    @PrimaryKey var login: String,
    var avatarUrl: String? = null
)

internal fun UserEntity.toDomain(): User {
    return User(
        login = login,
        avatarUrl = avatarUrl
    )
}
