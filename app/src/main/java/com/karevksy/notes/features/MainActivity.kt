package com.karevksy.notes.features

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.karevksy.core.ui.theme.NotesTheme
import com.karevksy.notes.features.notes.components.NotesScreen
import com.karevksy.notes.features.registration.components.RegistrationScreen
import com.karevksy.notes.features.util.Screens
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            NotesTheme {
                Surface(color = MaterialTheme.colors.background) {
                    val navController = rememberNavController()
                    NavHost(
                        navController = navController,
                        startDestination = Screens.RegistrationScreen.route
                    ) {
                        composable(Screens.RegistrationScreen.route) {
                            RegistrationScreen(navController = navController)
                        }
                    }
                }
            }
        }
    }
}