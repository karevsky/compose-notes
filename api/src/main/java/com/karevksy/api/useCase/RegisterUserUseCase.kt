package com.karevksy.api.useCase

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import javax.inject.Inject

interface RegisterUserUseCase {
    operator fun invoke(
        email: String,
        password: String,
        onSuccess: (user: FirebaseUser?) -> Unit,
        onError: (error: Exception?) -> Unit
    )
}

class RegisterUserUseCaseImpl @Inject constructor(
    private val auth: FirebaseAuth
) : RegisterUserUseCase {
    override fun invoke(
        email: String,
        password: String,
        onSuccess: (user: FirebaseUser?) -> Unit,
        onError: (error: Exception?) -> Unit
    ) {
        auth.createUserWithEmailAndPassword(email, password)
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    onSuccess(auth.currentUser)
                } else {
                    onError(task.exception)
                }
            }
    }
}