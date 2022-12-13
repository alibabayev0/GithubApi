package com.alibabayev.githubapi.domain.model

data class User(
    var login: String,
    var avatarUrl: String? = null
)
