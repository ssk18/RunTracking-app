@file:OptIn(ExperimentalFoundationApi::class)

package com.ssk.auth.presentation.login

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.text2.input.textAsFlow
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ssk.auth.domain.domain.AuthRepository
import com.ssk.auth.domain.domain.UserDataValidator
import com.ssk.auth.presentation.R
import com.ssk.core.domain.util.DataError
import com.ssk.core.presentation.ui.UiText
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import com.ssk.core.domain.util.Result
import com.ssk.core.presentation.ui.asUiText

class LoginViewModel(
    private val authRepository: AuthRepository,
    private val userDataValidator: UserDataValidator
) : ViewModel() {


    var state by mutableStateOf(LoginState())
        private set

    init {
        combine(state.email.textAsFlow(), state.password.textAsFlow()) { email, password ->
            state = state.copy(
                canLogin = userDataValidator.isValidEmail(
                    email.toString().trim()
                ) && password.isNotEmpty()
            )
        }.launchIn(viewModelScope)
    }

    private val eventChannel = Channel<LoginEvents>()
    val event = eventChannel.receiveAsFlow()

    fun onAction(action: LoginAction) {
        when (action) {
            is LoginAction.OnLoginClick -> login()
            is LoginAction.OnTogglePasswordVisibilityClick -> {
                state = state.copy(
                    isPasswordVisible = !state.isPasswordVisible
                )
            }

            else -> Unit
        }
    }

    private fun login() {
        viewModelScope.launch {
            state = state.copy(isLoggingIn = true)
            val result = authRepository.login(
                email = state.email.text.toString(),
                password = state.password.text.toString()
            )
            state = state.copy(isLoggingIn = false)
            when (result) {
                is Result.Success -> {
                    eventChannel.send(LoginEvents.OnLoginSuccess)
                }

                is Result.Error -> {
                    if (result.error == DataError.Network.UNAUTHORIZED) {
                        eventChannel.send(
                            LoginEvents.Error(
                                UiText.StringResource(R.string.email_or_password_is_incorrect)
                            )
                        )
                    } else {
                        eventChannel.send(LoginEvents.Error(result.error.asUiText()))
                    }
                }
            }
        }
    }

}