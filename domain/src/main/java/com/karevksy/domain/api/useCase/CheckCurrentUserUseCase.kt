package com.karevksy.domain.api.useCase

import com.google.firebase.auth.FirebaseAuth
import com.karevksy.core.utils.androidAsync
import com.karevksy.domain.database.useCase.user.DeleteUserUseCase
import io.reactivex.rxjava3.core.Single
import javax.inject.Inject

interface CheckCurrentUserUseCase {
    operator fun invoke(): Single<Boolean>
}

class CheckCurrentUserUseCaseImpl @Inject constructor(
    private val auth: FirebaseAuth,
    private val deleteUserUseCase: DeleteUserUseCase
) : CheckCurrentUserUseCase {
    override fun invoke(): Single<Boolean> {
        return if (auth.currentUser == null) {
            deleteUserUseCase()
                .androidAsync()
                .toSingle { true }
        } else { Single.just(false) }
    }
}