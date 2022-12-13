package com.alibabayev.githubapi.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material.Colors
import androidx.compose.material.darkColors
import androidx.compose.material.lightColors
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

val DarkColorPalette = darkColors(
    primary = Color.White,
    primaryVariant = AppColors.Black_600,
    background = AppColors.Black_600
)

val LightColorPalette = lightColors(
    primary = Color.Black,
    primaryVariant = Color.White,
    background = AppColors.White_500
)

@Composable
fun getColorPalette(): Colors {
    return if (isSystemInDarkTheme()) {
        DarkColorPalette
    } else {
        LightColorPalette
    }
}
