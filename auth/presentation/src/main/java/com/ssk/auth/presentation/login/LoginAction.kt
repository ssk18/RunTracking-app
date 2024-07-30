package com.ssk.auth.presentation.login

sealed interface LoginAction {
    data object OnTogglePasswordVisibilityClick: LoginAction
    data object OnLoginClick: LoginAction
    data object OnRegisterClick: LoginAction
}
