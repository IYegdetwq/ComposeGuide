package com.champion.mycompose.navigation

sealed class Screen(val route: String) {
    data object Home : Screen("home")
    data object Demo : Screen("demo/{id}") {
        fun createRoute(id: String) = "demo/$id"
    }
}
