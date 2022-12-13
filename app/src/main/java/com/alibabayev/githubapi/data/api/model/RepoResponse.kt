package com.alibabayev.githubapi.data.api.model

import com.alibabayev.githubapi.data.database.entity.RepoEntity
import com.alibabayev.githubapi.domain.model.Repo
import com.google.gson.annotations.SerializedName
import java.util.Date

data class RepoResponse(
    @SerializedName("name") var name: String? = null,
    @SerializedName("updated_at") var updatedAt: Date? = null,
    @SerializedName("stargazers_count") var stargazersCount: Int? = null,
    @SerializedName("language") var language: String? = null,
    @SerializedName("html_url") var htmlUrl: String? = null,
    @SerializedName("owner") var owner: UserResponse
)

internal fun RepoResponse.toDomain(): Repo {
    return Repo(
        name = name ?: "",
        updatedAt = updatedAt,
        stargazersCount = stargazersCount ?: 0,
        htmlUrl = htmlUrl ?: "",
        language = language ?: ""
    )
}

internal fun RepoResponse.toEntity(): RepoEntity {
    return RepoEntity(
        name = name ?: "",
        updatedAt = updatedAt,
        stargazersCount = stargazersCount,
        language = language,
        htmlUrl = htmlUrl ?: "",
        owner = owner.toEntity()
    )
}
