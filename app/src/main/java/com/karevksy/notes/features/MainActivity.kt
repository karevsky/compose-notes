package com.karevksy.notes.features

import android.os.Bundle
import android.view.WindowManager
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.karevksy.core.ui.theme.NotesTheme
import com.karevksy.notes.features.auth.authorization.components.SignInScreen
import com.karevksy.notes.features.auth.registration.SignUpViewModel
import com.karevksy.notes.features.auth.registration.components.SignUpScreen
import com.karevksy.notes.features.notes.main.components.NotesScreen
import com.karevksy.notes.features.util.Screens
import dagger.hilt.android.AndroidEntryPoint

@ExperimentalFoundationApi
@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        window.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE)
        setContent {
            NotesTheme {
                Surface(color = MaterialTheme.colors.background) {
                    val navController = rememberNavController()
                    NavHost(
                        navController = navController,
                        startDestination = Screens.RegistrationScreen.route
                    ) {
                        composable(Screens.RegistrationScreen.route) {
                            val viewModel = hiltViewModel<SignUpViewModel>(it)
                            SignUpScreen(viewModel, navController)
                        }
                        composable(Screens.NotesScreen.route) {
                            NotesScreen(navController = navController)
                        }
                        composable(Screens.AuthenticationScreen.route) {
                            SignInScreen()
                        }
                        composable(Screens.CreateNoteScreen.route) {
                            TODO("Создать")
                        }
                    }
                }

            }
        }
    }
}