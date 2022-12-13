package com.alibabayev.githubapi.ui.theme

import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable

@Composable
fun GithubTheme(content: @Composable () -> Unit) {
    MaterialTheme(
        colors = getColorPalette(),
        typography = Type,
        content = content
    )
}
