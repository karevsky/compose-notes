package com.karevksy.notes.features.auth.signIn

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException
import com.karevksy.core.base.BaseViewModel
import com.karevksy.core.model.dto.LocalUser
import com.karevksy.core.utils.*
import com.karevksy.domain.api.useCase.SignInUseCase
import com.karevksy.domain.database.useCase.user.AddUserUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

data class SignInState(
    var buttonVisible: Boolean = false,
    var loading: Boolean = false,
    var signUp: Boolean = false
)

@HiltViewModel
class SignInViewModel @Inject constructor(
    private val signInUseCase: SignInUseCase,
    private val addUserUseCase: AddUserUseCase
) : BaseViewModel() {
    private var _uiState = MutableLiveData(SignInState())
    val uiState: LiveData<SignInState> = _uiState

    val navigateToNotes = LiveEvent()

    var email by mutableStateOf(InputWrapper("", null))
    var password by mutableStateOf(InputWrapper("", null))

    fun onEmailChanged(input: String) {
        email = email.copy(input = input)
        checkButton()
    }

    fun onPasswordChanged(input: String) {
        password = password.copy(input = input)
        checkButton()
    }

    private fun checkButton() {
        _uiState.value = _uiState.value?.copy(
            buttonVisible = (email.input.isEmpty() || password.input.length < 6).not()
        )
    }

    fun signInUser() {
        isLoading(true)

        signInUseCase(
            email = email.input,
            password = password.input,
            onSuccess = { navigateToNotes.invoke() },
            onError = {
                when(it) {
                    is FirebaseAuthInvalidCredentialsException ->
                        password = password.copy(error = "Неверная почта или e-mail")
                }
                isLoading(false)
            }
        )
    }

    private fun isLoading(loading: Boolean) {
        _uiState.value = _uiState.value?.copy(
            loading = loading,
            buttonVisible = loading.not()
        )
    }

    fun signInLocal() {
        addUserUseCase(LocalUser)
            .androidAsync()
            .subscribe { navigateToNotes() }
            .addToDisposable(compositeDisposable)
    }
}
