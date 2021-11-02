package com.karevksy.notes.features.auth.signIn

import android.content.Context
import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import com.google.firebase.auth.FirebaseAuthException
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException
import com.karevksy.core.base.BaseViewModel
import com.karevksy.core.model.dto.LocalUser
import com.karevksy.core.utils.*
import com.karevksy.domain.api.useCase.SignInUseCase
import com.karevksy.domain.database.useCase.user.AddUserUseCase
import com.karevksy.notes.R
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject

@HiltViewModel
class SignInViewModel @Inject constructor(
    private val signInUseCase: SignInUseCase,
    private val addUserUseCase: AddUserUseCase,
    @ApplicationContext private val context: Context
) : BaseViewModel() {
    companion object {
        const val TAG = "SignInViewModel"
    }

    var email by mutableStateOf(InputWrapper("", null))
    var password by mutableStateOf(InputWrapper("", null))
    var buttonVisible by mutableStateOf(false)

    val navigateToNotes = LiveEvent()
    val navigateToSignUp = LiveEvent()

    fun onEmailChanged(input: String) {
        email = email.copy(input = input)
        checkButton()
        dropErrors()
    }

    fun onPasswordChanged(input: String) {
        password = password.copy(input = input)
        checkButton()
        dropErrors()
    }

    private fun dropErrors() {
        email.dropError()
        password.dropError()
    }

    private fun checkButton() {
        buttonVisible = (email.input.isEmpty() || password.input.length < 6).not()
    }

    fun signInUser() {
        isLoading(true)

        signInUseCase(
            email = email.input,
            password = password.input,
            onSuccess = {
                isLoading(false)
                navigateToNotes.invoke()
            },
            onError = {
                when(it) {
                    is FirebaseAuthInvalidCredentialsException ->
                        password = password.copy(
                            error = context.getString(R.string.invalid_credentials)
                        )
                    else -> Log.e(TAG, it.toString())
                }
                isLoading(false)
            }
        )
    }

    private fun isLoading(isLoading: Boolean) {
        loading = isLoading
        buttonVisible = isLoading
    }

    fun signInLocal() {
        addUserUseCase(LocalUser)
            .androidAsync()
            .subscribe { navigateToNotes() }
            .addToDisposable(compositeDisposable)
    }
}
