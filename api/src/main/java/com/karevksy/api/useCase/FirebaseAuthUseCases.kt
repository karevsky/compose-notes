package com.karevksy.api.useCase

data class FirebaseAuthUseCases(
    val authenticateUserUseCase: AuthenticateUserUseCase,
    val checkCurrentUserUseCase: CheckCurrentUserUseCase,
    val registerUserUseCase: RegisterUserUseCase
)
