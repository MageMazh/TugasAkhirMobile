package com.D121211069.catpedia.ui.screens.main

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.D121211069.catpedia.ui.screens.detail.DetailScreen
import com.D121211069.catpedia.ui.screens.home.HomeScreen
import com.D121211069.catpedia.ui.screens.search.SearchScreen
import com.D121211069.catpedia.ui.screens.setting.SettingScreen
import com.D121211069.catpedia.ui.viewmodel.CatpediaViewModel

@Composable
fun CatpediaApp(
    viewModel: CatpediaViewModel = androidx.lifecycle.viewmodel.compose.viewModel(),
    navController: NavHostController = rememberNavController()
){

    CatpediaContent(
        viewModel = viewModel,
        navController = navController,
    )
}

@Composable
fun CatpediaContent(
    viewModel: CatpediaViewModel,
    navController: NavHostController,
){
    NavHost(navController, startDestination = "home") {
        composable("home") {
            HomeScreen(viewModel, navController)
        }
        composable("search") {
            SearchScreen(viewModel, navController)
        }
        composable("setting") {
            SettingScreen(navController)
        }
        composable("detail/{catId}") { backStackEntry ->
            val arguments = requireNotNull(backStackEntry.arguments)
            val catId = arguments.getString("catId") ?: error("Missing catId argument")

            DetailScreen(catId, viewModel, navController)
        }
    }
}