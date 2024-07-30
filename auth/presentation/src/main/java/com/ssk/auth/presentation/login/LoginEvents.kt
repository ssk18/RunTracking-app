package com.ssk.auth.presentation.login

import com.ssk.core.presentation.ui.UiText

sealed interface LoginEvents {
    data object OnLoginSuccess: LoginEvents
    data class Error(val error: UiText): LoginEvents
}