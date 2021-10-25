package com.karevksy.notes.features.auth.registration

import android.content.Context
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.firebase.auth.*
import com.karevksy.api.R
import com.karevksy.api.useCase.FirebaseAuthUseCases
import com.karevksy.core.util.InputWrapper
import com.karevksy.notes.features.util.Screens
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import io.reactivex.rxjava3.core.Flowable
import io.reactivex.rxjava3.core.Observable
import javax.inject.Inject

data class SignUpState(
    var loading: Boolean = false,
    var buttonVisible: Boolean = false,
    var successSignUp: Boolean = false
)

@HiltViewModel
class SignUpViewModel @Inject constructor(
    private val firebaseAuthUseCases: FirebaseAuthUseCases,
    @ApplicationContext private val context: Context
) : ViewModel() {
    var email by mutableStateOf(InputWrapper("", null))
    var password by mutableStateOf(InputWrapper("", null))
    var repeatPassword by mutableStateOf(InputWrapper("", null))

    private var _uiState = MutableLiveData(SignUpState())
    var uiState: LiveData<SignUpState> = _uiState

    fun onEmailChanged(input: String) {
        email = email.copy(input = input)
        dropErrors()
        checkButton()
    }

    fun onPasswordChanged(input: String) {
        password = password.copy(input = input)
        dropErrors()
        checkButton()
    }

    fun onRepeatPasswordChanged(input: String) {
        repeatPassword = repeatPassword.copy(input = input)
        dropErrors()
        checkButton()
    }

    private fun dropErrors() {
        email.dropError()
        password.dropError()
        repeatPassword.dropError()
    }

    private fun checkButton() {
        _uiState.value = _uiState.value?.copy(
            buttonVisible = (email.input.isEmpty() ||
                    password.input.length < 6 ||
                    repeatPassword.input.length < 6).not()
        )
    }

    fun registerNewUser() {
        uiLoading(true)
        if (password != repeatPassword) {
            repeatPassword = repeatPassword.copy(
                error = context.getString(com.karevksy.notes.R.string.password_not_identical)
            )
            uiLoading(false)
            return
        }
        firebaseAuthUseCases.registerUserUseCase(
            email.input,
            password.input,
            onError = { errorCode ->
                uiLoading(false)
                when (errorCode) {
                    is FirebaseAuthInvalidCredentialsException ->
                        email = email.copy(
                            error = context.getString(R.string.invalid_email)
                        )
                    is FirebaseAuthWeakPasswordException ->
                        password = password.copy(
                            error = context.getString(R.string.weak_password)
                        )
                    is FirebaseAuthUserCollisionException ->
                        email = email.copy(
                            error = context.getString(com.karevksy.notes.R.string.email_collision_error)
                        )
                }
            },
            onSuccess = {
                _uiState.value?.successSignUp = true
                uiLoading(false)
            }
        )
    }

    private fun uiLoading(isLoading: Boolean) {
        _uiState.value = _uiState.value?.copy(
            loading = isLoading,
            buttonVisible = isLoading.not()
        )
    }
}

