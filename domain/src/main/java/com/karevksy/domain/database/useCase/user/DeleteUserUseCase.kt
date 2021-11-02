package com.karevksy.domain.database.useCase.user

import com.karevksy.core.model.dto.User
import com.karevksy.domain.database.repository.UserRepository
import io.reactivex.rxjava3.core.Completable

interface DeleteUserUseCase {
    operator fun invoke(): Completable
}

class DeleteUserUseCaseImpl(private val repository: UserRepository): DeleteUserUseCase {
    override fun invoke(): Completable =
        repository.deleteUser()
}