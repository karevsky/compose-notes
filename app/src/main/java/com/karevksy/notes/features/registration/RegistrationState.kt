package com.karevksy.notes.features.registration

import com.karevksy.core.util.Constants

data class RegistrationState(
    private val isLoading: Boolean = false,
    private val toastMessage: String = Constants.EMPTY_STRING
)
