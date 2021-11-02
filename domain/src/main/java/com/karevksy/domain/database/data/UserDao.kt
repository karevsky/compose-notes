package com.karevksy.domain.database.data

import androidx.room.*
import com.karevksy.core.model.entity.UserEntity
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Single

@Dao
interface UserDao {
    @Query("SELECT * FROM user")
    fun getUser(): Single<UserEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun addUser(user: UserEntity): Completable

    @Query("DELETE FROM user")
    fun deleteUser(): Completable
}