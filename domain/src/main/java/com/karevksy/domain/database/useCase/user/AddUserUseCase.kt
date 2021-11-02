package com.karevksy.domain.database.useCase.user

import com.karevksy.core.model.dto.User
import com.karevksy.domain.database.repository.UserRepository
import io.reactivex.rxjava3.core.Completable

interface AddUserUseCase {
    operator fun invoke(user: User): Completable
}

class AddUserUseCaseImpl(private val repository: UserRepository): AddUserUseCase {
    override fun invoke(user: User): Completable =
        repository.addUser(user = user.toEntity())
}