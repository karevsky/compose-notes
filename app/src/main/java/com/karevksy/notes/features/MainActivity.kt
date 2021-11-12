package com.karevksy.notes.features

import android.os.Bundle
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
import com.karevksy.core.utils.Screens
import com.karevksy.notes.features.auth.signIn.SignInViewModel
import com.karevksy.notes.features.auth.signIn.components.SignInScreen
import com.karevksy.notes.features.auth.signUp.SignUpViewModel
import com.karevksy.notes.features.auth.signUp.components.SignUpScreen
import com.karevksy.notes.features.notes.createNote.CreateNoteViewModel
import com.karevksy.notes.features.notes.createNote.components.CreateNoteScreen
import com.karevksy.notes.features.notes.main.NotesViewModel
import com.karevksy.notes.features.notes.main.components.NotesScreen
import dagger.hilt.android.AndroidEntryPoint

@ExperimentalFoundationApi
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
                        startDestination = Screens.SignInScreen.route
                    ) {
                        composable(Screens.SignUpScreen.route) {
                            val viewModel: SignUpViewModel = hiltViewModel(it)
                            SignUpScreen(viewModel, navController)
                        }
                        composable(Screens.NotesScreen.route) {
                            val viewModel: NotesViewModel = hiltViewModel(it)
                            NotesScreen(viewModel = viewModel, navController = navController)
                        }
                        composable(Screens.SignInScreen.route) {
                            val viewModel: SignInViewModel = hiltViewModel(it)
                            SignInScreen(viewModel, navController)
                        }
                        composable(Screens.CreateNoteScreen.route) {
                            val viewModel: CreateNoteViewModel = hiltViewModel(it)
                            CreateNoteScreen(viewModel = viewModel, navController = navController)
                        }
                    }
                }
            }
        }
    }
}