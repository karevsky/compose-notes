package com.karevksy.api.useCase

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import javax.inject.Inject

interface AuthenticateUserUseCase {
    operator fun invoke(
        email: String,
        password: String,
        onSuccess: (user: FirebaseUser?) -> Unit,
        onError: (error: Exception?) -> Unit
    )
}

class AuthenticateUserUseCaseImpl @Inject constructor(
    private val auth: FirebaseAuth
) : AuthenticateUserUseCase {
    override fun invoke(
        email: String,
        password: String,
        onSuccess: (user: FirebaseUser?) -> Unit,
        onError: (error: Exception?) -> Unit
    ) {
        auth.signInWithEmailAndPassword(email, password)
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    onSuccess(auth.currentUser)
                } else {
                    onError(task.exception)
                }
            }
    }
}