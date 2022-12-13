package com.alibabayev.githubapi.ui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.alibabayev.githubapi.ui.screen.repos.UserReposScreen
import com.alibabayev.githubapi.ui.screen.users.UsersScreen

@Composable
fun RootNavGraph(navController: NavHostController) {
    NavHost(
        navController = navController,
        startDestination = ScreenRoute.UsersRoute.route
    ) {
        composable(route = ScreenRoute.UsersRoute.route) {
            UsersScreen(navigateToRepo = {
                navController.navigate(ScreenRoute.UserReposRoute.passArguments(it))
            })
        }
        composable(route = ScreenRoute.UserReposRoute.route) { navBackStackEntry ->
            val login = navBackStackEntry.arguments?.getString("login")
            login?.let {
                UserReposScreen(
                    navigateToBack = { navController.navigateUp() },
                    login = it
                )
            }
        }
    }
}

sealed class ScreenRoute(val route: String) {
    object UsersRoute : ScreenRoute(route = "users_screen")
    object UserReposRoute : ScreenRoute(route = "user_repos_screen/{login}") {
        fun passArguments(login: String): String {
            return "user_repos_screen/$login"
        }
    }
}
