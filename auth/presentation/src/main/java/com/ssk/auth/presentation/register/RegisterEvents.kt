package com.ssk.auth.presentation.register

import com.ssk.core.presentation.ui.UiText

sealed interface RegisterEvents {
    data object OnRegistrationSuccess: RegisterEvents
    data class Error(val error: UiText): RegisterEvents
}