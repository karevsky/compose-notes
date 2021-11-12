package com.karevksy.core.model.dto

import android.os.Parcelable
import com.karevksy.core.model.entity.NoteEntity
import com.karevksy.core.utils.Constants
import kotlinx.parcelize.Parcelize

@Parcelize
data class Note(
    val id: Int = 0,
    val title: String = Constants.EMPTY_STRING,
    var content: String = Constants.EMPTY_STRING,
    val timestamp: Long = 0L,
    val isFixed: Boolean = false,
    val images: ArrayList<String>? = null
): Parcelable {
    fun toEntity(): NoteEntity = NoteEntity(
        id = 0,
        title = this.title,
        content = this.content,
        timestamp = this.timestamp,
        isFixed = this.isFixed,
        images = this.images
    )

    fun isContentEmpty(): Boolean {
        return if (content == Constants.EMPTY_STRING) {
            this.content = "Описание отсутствует"
            true
        } else false
    }
}