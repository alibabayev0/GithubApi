package com.alibabayev.githubapi.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.navigation.compose.rememberNavController
import com.alibabayev.githubapi.ui.navigation.RootNavGraph
import com.alibabayev.githubapi.ui.theme.GithubTheme
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            GithubTheme {
                GithubApp()
            }
        }
    }

    @Composable
    fun GithubApp() {
        val navController = rememberNavController()
        RootNavGraph(navController)
        SetupSystemUI()
    }

    @Composable
    private fun SetupSystemUI(isSystemUiEnabled: Boolean = true) {
        val systemUiController = rememberSystemUiController()

        systemUiController.isSystemBarsVisible = isSystemUiEnabled
        systemUiController.setStatusBarColor(
            color = Color.Transparent,
            darkIcons = !isSystemInDarkTheme()
        )
        systemUiController.setNavigationBarColor(
            color = Color.Transparent,
            darkIcons = isSystemInDarkTheme()
        )
    }
}
