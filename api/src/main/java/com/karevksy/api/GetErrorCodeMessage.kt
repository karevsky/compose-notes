package com.karevksy.api

import android.content.Context
import dagger.hilt.android.qualifiers.ApplicationContext

interface GetErrorCodeMessage {
    operator fun invoke(errorCode: String): String
}

class GetErrorCodeMessageImpl(
    @ApplicationContext val context: Context,
): GetErrorCodeMessage {
    override fun invoke(errorCode: String): String = when (errorCode) {
        ErrorCodes.ERROR_INVALID_EMAIL.name -> context.getString(R.string.invalid_email)
        ErrorCodes.ERROR_WEAK_PASSWORD.name -> context.getString(R.string.weak_password)
        else -> context.getString(R.string.default_error)
    }
}