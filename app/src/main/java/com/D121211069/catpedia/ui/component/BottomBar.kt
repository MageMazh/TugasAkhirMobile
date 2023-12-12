package com.D121211069.catpedia.ui.component

import androidx.compose.foundation.background
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.navigation.NavHostController

data class BottomBarItem(val title: String, val icon: ImageVector, val route: String)

@Composable
fun BottomBar(navController: NavHostController, modifier: Modifier = Modifier) {
    NavigationBar(
        modifier = modifier.background(color = Color.Red),
    ) {
        val bottomNavigation = listOf(
            BottomBarItem(
                title = "home",
                icon = Icons.Default.Home,
                route = "home"
            ),
            BottomBarItem(
                title = "Search",
                icon = Icons.Default.Search,
                route = "search"
            ),
            BottomBarItem(
                title = "Setting",
                icon = Icons.Default.Settings,
                route = "setting"
            ),
        )
        bottomNavigation.map { item ->
            NavigationBarItem(
                selected = navController.currentDestination?.route == item.route,
                onClick = { navController.navigate(item.route) },
                icon = { Icon(imageVector = item.icon, contentDescription = item.title) },
                label = { Text(text = item.title) },
            )
        }
    }
}
