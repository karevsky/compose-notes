package com.karevksy.core.util

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class InputWrapper(
    var input: String,
    var error: String?
): Parcelable {
    fun dropError() { this.error = null }
    fun isError(): Boolean = error != null
}