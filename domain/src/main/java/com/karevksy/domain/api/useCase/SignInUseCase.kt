package com.karevksy.domain.api.useCase

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.karevksy.core.utils.androidAsync
import com.karevksy.core.model.dto.User
import com.karevksy.domain.database.useCase.user.AddUserUseCase
import javax.inject.Inject

interface SignInUseCase {
    operator fun invoke(
        email: String,
        password: String,
        onSuccess: (user: FirebaseUser?) -> Unit,
        onError: (error: Exception?) -> Unit
    )
}

class SignInUseCaseImpl @Inject constructor(
    private val auth: FirebaseAuth,
    private val addUserUseCase: AddUserUseCase
) : SignInUseCase {
    override fun invoke(
        email: String,
        password: String,
        onSuccess: (user: FirebaseUser?) -> Unit,
        onError: (error: Exception?) -> Unit
    ) {
        auth.signInWithEmailAndPassword(email, password)
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    val user = task.result?.user
                    user?.let {
                        addUserUseCase(User(it.uid, it.email!!))
                            .androidAsync()
                            .subscribe { onSuccess(it) }
                    }
                } else {
                    onError(task.exception)
                }
            }
    }
}