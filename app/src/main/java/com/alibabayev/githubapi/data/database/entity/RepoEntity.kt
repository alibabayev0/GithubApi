package com.alibabayev.githubapi.data.database.entity

import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.alibabayev.githubapi.domain.model.Repo
import java.util.Date

@Entity(tableName = "repos")
data class RepoEntity(
    @PrimaryKey
    var name: String,
    var updatedAt: Date? = null,
    var stargazersCount: Int? = null,
    var language: String? = null,
    val htmlUrl: String? = null,
    @Embedded
    var owner: UserEntity? = null
)

internal fun RepoEntity.toDomain(): Repo {
    return Repo(
        name = name,
        updatedAt = updatedAt ?: null,
        stargazersCount = stargazersCount ?: 0,
        htmlUrl = htmlUrl ?: "",
        language = language ?: ""
    )
}
