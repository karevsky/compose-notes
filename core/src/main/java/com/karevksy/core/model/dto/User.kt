package com.karevksy.core.model.dto

import android.os.Parcelable
import com.karevksy.core.model.entity.UserEntity
import kotlinx.parcelize.Parcelize

@Parcelize
open class User(
    val id: String,
    val email: String
): Parcelable {
    fun toEntity(): UserEntity = UserEntity(
        this.id,
        this.email
    )
}

object LocalUser: User(
    id = "local",
    email = "local"
)