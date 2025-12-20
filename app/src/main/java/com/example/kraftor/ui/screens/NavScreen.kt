package com.example.kraftor.ui.screens

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.kraftor.R
import com.example.kraftor.presentation.viewmodels.GenerateImageViewModel
import com.example.kraftor.presentation.viewmodels.GenerateTextViewModel



private data class BottomNavItem(
    val label: String,
    val route: String,
    val icon: Int
)

@Composable
fun MainAppScreen() {
    val navController = rememberNavController()

    // List of items to show in the bottom bar
    val navItems = listOf(

        BottomNavItem("Generate Text", "text", R.drawable.ic_launcher_foreground),
        BottomNavItem("Generate Image", "image", R.drawable.ic_launcher_foreground)
    )

    Scaffold(
        bottomBar = {
            AppBottomNavigationBar(
                items = navItems,
                navController = navController,
                onItemClick = { route ->
                    navController.navigate(route) {
                        // Pop up to the start destination of the graph to
                        // avoid building up a large stack of destinations
                        // on the back stack as users select items
                        popUpTo(navController.graph.findStartDestination().id) {
                            saveState = true
                        }
                        // Avoid multiple copies of the same destination when
                        // re-selecting the same item
                        launchSingleTop = true
                        // Restore state when re-selecting a previously selected item
                        restoreState = true
                    }
                }
            )
        }
    ) { innerPadding ->
        // The NavHost that contains the screens accessible from the bottom bar
        NavHost(
            navController = navController,
            startDestination = "text",
            modifier = Modifier.padding(innerPadding)
        ) {
            composable("text") {

                val textViewModel: GenerateTextViewModel = viewModel()
                GenerateTextScreen(viewModel = textViewModel)
            }
            composable("image") {

                val imageViewModel: GenerateImageViewModel = viewModel()
                GenerateImageScreen(viewModel = imageViewModel)
            }
        }
    }
}

@Composable
private fun AppBottomNavigationBar(
    items: List<BottomNavItem>,
    navController: NavController,
    onItemClick: (String) -> Unit
) {
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route

    NavigationBar {
        items.forEach { item ->
            NavigationBarItem(
                selected = currentRoute == item.route,
                onClick = { onItemClick(item.route) },
                icon = { Icon(painterResource(id = item.icon), contentDescription = item.label) },
                label = { Text(item.label) }
            )
        }
    }
}


@Preview(showBackground = true)
@Composable
private fun MainAppScreenPreview() {
    MainAppScreen()
    // }
}