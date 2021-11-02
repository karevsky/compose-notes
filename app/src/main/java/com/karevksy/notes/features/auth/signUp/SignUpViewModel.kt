package com.karevksy.notes.features.auth.signUp

import android.content.Context
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.firebase.auth.*
import com.karevksy.core.utils.InputWrapper
import com.karevksy.core.utils.LiveEvent
import com.karevksy.domain.api.useCase.RegisterUserUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject

data class SignUpState(
    var loading: Boolean = false,
    var buttonVisible: Boolean = false
)

@HiltViewModel
class SignUpViewModel @Inject constructor(
    private val signUpUserUseCase: RegisterUserUseCase,
    @ApplicationContext private val context: Context
) : ViewModel() {
    var email by mutableStateOf(InputWrapper("", null))
    var password by mutableStateOf(InputWrapper("", null))
    var repeatPassword by mutableStateOf(InputWrapper("", null))
    val navigateToSignIn = LiveEvent()

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
                error = "Пароли совпадают."
            )
            uiLoading(false)
            return
        }
        signUpUserUseCase(
            email.input,
            password.input,
            onError = { errorCode ->
                uiLoading(false)
                when (errorCode) {
                    is FirebaseAuthInvalidCredentialsException ->
                        email = email.copy(
                            error = "Некорректно введена почта"
                        )
                    is FirebaseAuthWeakPasswordException ->
                        password = password.copy(
                            error = "Слишком слабый пароль"
                        )
                    is FirebaseAuthUserCollisionException ->
                        email = email.copy(
                            error = "E-mail уже используется"
                        )
                }
            },
            onSuccess = {
                navigateToSignIn.invoke()
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

