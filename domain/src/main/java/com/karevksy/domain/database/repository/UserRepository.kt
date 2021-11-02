package com.karevksy.domain.database.repository

import com.karevksy.core.model.entity.UserEntity
import com.karevksy.domain.database.data.UserDao
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Single

interface UserRepository {
    fun addUser(user: UserEntity): Completable
    fun deleteUser(): Completable
    fun getUser(): Single<UserEntity>
}

class UserRepositoryImpl(
    private val dao: UserDao
) : UserRepository {
    override fun addUser(user: UserEntity): Completable =
        dao.addUser(user)

    override fun deleteUser(): Completable =
        dao.deleteUser()

    override fun getUser(): Single<UserEntity> =
        dao.getUser()
}