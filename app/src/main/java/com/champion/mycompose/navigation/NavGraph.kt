package com.champion.mycompose.navigation

import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.champion.mycompose.BasicLayoutDemo
import com.champion.mycompose.ui.demo.ComingSoonScreen
import com.champion.mycompose.ui.home.HomeScreen
import com.champion.mycompose.ui.home.demoItems

@Composable
fun AppNavGraph(navController: NavHostController) {
    NavHost(
        navController = navController,
        startDestination = Screen.Home.route
    ) {
        composable(Screen.Home.route) {
            HomeScreen(
                onDemoClick = { id ->
                    navController.navigate(Screen.Demo.createRoute(id))
                }
            )
        }
        composable(
            route = Screen.Demo.route,
            arguments = listOf(navArgument("id") { type = NavType.StringType })
        ) { backStackEntry ->
            val demoId = backStackEntry.arguments?.getString("id") ?: ""
            val demoTitle = demoItems.find { it.id == demoId }?.title ?: ""
            DemoScaffold(
                title = demoTitle,
                onBack = { navController.popBackStack() }
            ) { modifier ->
                when (demoId) {
                    "basic_layout" -> BasicLayoutDemo(modifier = modifier)
                    else -> ComingSoonScreen(title = demoTitle, modifier = modifier)
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun DemoScaffold(
    title: String,
    onBack: () -> Unit,
    content: @Composable (Modifier) -> Unit
) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(text = title) },
                navigationIcon = {
                    IconButton(onClick = onBack) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                            contentDescription = "返回"
                        )
                    }
                }
            )
        }
    ) { innerPadding ->
        content(Modifier.padding(innerPadding))
    }
}
