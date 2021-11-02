package com.karevksy.notes.features.auth.signUp

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException
import com.google.firebase.auth.FirebaseAuthUserCollisionException
import com.google.firebase.auth.FirebaseAuthWeakPasswordException
import com.karevksy.core.base.BaseViewModel
import com.karevksy.core.utils.InputWrapper
import com.karevksy.core.utils.LiveEvent
import com.karevksy.domain.api.useCase.RegisterUserUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class SignUpViewModel @Inject constructor(
    private val signUpUserUseCase: RegisterUserUseCase,
) : BaseViewModel() {

    companion object {
        const val TAG = "SignUpViewModel"
    }

    var email by mutableStateOf(InputWrapper("", null))
    var password by mutableStateOf(InputWrapper("", null))
    var repeatPassword by mutableStateOf(InputWrapper("", null))
    var buttonVisible by mutableStateOf(false)

    val navigateToSignIn = LiveEvent()

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
        buttonVisible =
            (email.input.isEmpty() || password.input.length < 6 || repeatPassword.input.length < 6)
                .not()
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
            onError = {
                uiLoading(false)
                when (it) {
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
                    else -> Log.d(TAG, it.toString())
                }
            },
            onSuccess = {
                navigateToSignIn.invoke()
                uiLoading(false)
            }
        )
    }

    private fun uiLoading(isLoading: Boolean) {
        loading = isLoading
        buttonVisible = isLoading.not()
    }
}

