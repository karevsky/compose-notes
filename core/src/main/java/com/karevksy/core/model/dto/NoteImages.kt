package com.karevksy.core.model.dto

import android.os.Parcelable
import com.karevksy.core.utils.Constants
import kotlinx.parcelize.Parcelize

@Parcelize
data class NoteImages(
    val localPath: String = Constants.EMPTY_STRING
): Parcelable