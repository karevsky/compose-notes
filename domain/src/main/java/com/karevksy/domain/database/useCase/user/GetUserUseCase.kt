package com.karevksy.domain.database.useCase.user

import com.karevksy.core.model.dto.User
import com.karevksy.domain.database.repository.UserRepository
import io.reactivex.rxjava3.core.Single

interface GetUserUseCase {
    operator fun invoke(): Single<User>
}

class GetUserUseCaseImpl(private val repository: UserRepository): GetUserUseCase {
    override fun invoke(): Single<User> =
        repository.getUser().map { it.toDto() }
}