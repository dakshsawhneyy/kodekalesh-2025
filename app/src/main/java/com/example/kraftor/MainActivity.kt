package com.example.kraftor

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
// FIX 1: Import TokenStorage from the correct package
import com.example.kraftor.data.local.TokenStorage
import com.example.kraftor.ui.screens.AuthScreen
import com.example.kraftor.ui.screens.MainAppScreen
import com.example.kraftor.ui.theme.KraftoRTheme

object AppRoutes {
    const val AUTH = "authentication"
    const val MAIN = "main_application"
}

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            KraftoRTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    AppNavigationGraph()
                }
            }
        }
    }
}

@Composable
fun AppNavigationGraph() {
    val navController = rememberNavController()

    val context = LocalContext.current
    val tokenStorage = remember { TokenStorage(context) }

    val token = tokenStorage.getToken()

    val startDestination = if (!token.isNullOrBlank()) {
        AppRoutes.MAIN // Token exists, start in the main app
    } else {
        AppRoutes.AUTH   // Token is null or empty, start at Login
    }

    NavHost(navController = navController, startDestination = startDestination) {
        // Authentication Screen
        composable(AppRoutes.AUTH) {
            AuthScreen(
                onAuthSuccess = {
                    // navigate to the main app and clear the back stack
                    navController.navigate(AppRoutes.MAIN) {
                        popUpTo(AppRoutes.AUTH) {
                            inclusive = true
                        }
                    }
                }
            )
        }

        composable(AppRoutes.MAIN) {
            MainAppScreen()
        }
    }
}


@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    KraftoRTheme {
        AppNavigationGraph()
    }
}
