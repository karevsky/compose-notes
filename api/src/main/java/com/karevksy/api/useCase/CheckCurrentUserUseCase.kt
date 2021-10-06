package com.karevksy.api.useCase

import com.google.firebase.auth.FirebaseAuth
import javax.inject.Inject

interface CheckCurrentUserUseCase {
    operator fun invoke(): Boolean
}

class CheckCurrentUserUseCaseImpl @Inject constructor(
    private val auth: FirebaseAuth
) : CheckCurrentUserUseCase {
    override fun invoke(): Boolean {
        return auth.currentUser != null
    }
}