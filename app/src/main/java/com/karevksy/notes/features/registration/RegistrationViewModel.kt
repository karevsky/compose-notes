package com.karevksy.notes.features.registration

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.navigation.NavController
import com.karevksy.api.useCase.FirebaseAuthUseCases
import com.karevksy.notes.features.util.Screens
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class RegistrationViewModel @Inject constructor(
    private val firebaseAuthUseCases: FirebaseAuthUseCases,
): ViewModel() {
    private val _state = mutableStateOf(RegistrationState())
    val state: State<RegistrationState> = _state

    fun onEvent(event: RegistrationEvent) {
        when(event) {
            is RegistrationEvent.OnRegisterNewUserEvent -> {
                firebaseAuthUseCases.registerUserUseCase(
                    event.email,
                    event.password,
                    onSuccess = { user ->
                        println(user?.email)
                    },
                    onError = { error ->
                        println(error?.localizedMessage)
                    }
                )
            }
        }
    }


}