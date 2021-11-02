package com.karevksy.core.model.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.karevksy.core.model.dto.User
import javax.inject.Named

@Entity(tableName = "user")
data class UserEntity(
    @PrimaryKey val id: String,
    @ColumnInfo(name = "email") val email: String
) {
    fun toDto(): User = User(
        id = this.id,
        email = this.email
    )
}
