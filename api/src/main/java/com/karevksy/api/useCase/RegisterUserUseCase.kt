package com.karevksy.api.useCase

import android.content.Context
import com.google.firebase.auth.*
import com.karevksy.api.R
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject

interface RegisterUserUseCase {
    operator fun invoke(
        email: String,
        password: String,
        onSuccess: (user: FirebaseUser?) -> Unit,
        onError: (exception: FirebaseAuthException) -> Unit,
    )
}

class RegisterUserUseCaseImpl @Inject constructor(
    private val auth: FirebaseAuth,
) : RegisterUserUseCase {

    override fun invoke(
        email: String,
        password: String,
        onSuccess: (user: FirebaseUser?) -> Unit,
        onError: (exception: FirebaseAuthException) -> Unit,
    ) {
        auth.createUserWithEmailAndPassword(email, password)
            .addOnCompleteListener { task ->
                if (task.isSuccessful) { onSuccess(auth.currentUser) }
                else { onError(task.exception as FirebaseAuthException) }
            }
    }
}