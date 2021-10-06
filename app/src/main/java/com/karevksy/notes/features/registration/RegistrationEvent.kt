package com.karevksy.notes.features.registration

sealed class RegistrationEvent {
    data class OnRegisterNewUserEvent(
        val email: String,
        val password: String
    ): RegistrationEvent()
}
