package com.karevksy.core.utils

sealed class Screens(val route: String) {
    object NotesScreen: Screens("notes_screen")
    object CreateNoteScreen: Screens("create_note_screen")
    object SignInScreen: Screens("sign_in_screen")
    object SignUpScreen: Screens("sign_up_screen")
}