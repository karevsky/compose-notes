package com.karevksy.domain.api.useCase

import com.google.firebase.auth.*
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