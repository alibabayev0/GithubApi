package com.alibabayev.githubapi.domain.model

import java.util.Date

data class Repo(
    var name: String,
    var updatedAt: Date? = null,
    var stargazersCount: Int,
    val htmlUrl: String,
    var language: String? = null
)
