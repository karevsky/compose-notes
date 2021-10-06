package com.karevksy.notes.features.util

sealed class Screens(val route: String) {
    object NotesScreen: Screens("notes_screen")
    object CreateNoteScreen: Screens("create_note_screen")
    object AuthenticationScreen: Screens("authentication_screen")
    object RegistrationScreen: Screens("registration_screen")
}
