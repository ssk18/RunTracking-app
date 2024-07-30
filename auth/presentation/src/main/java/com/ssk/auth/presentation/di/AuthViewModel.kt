@file:OptIn(ExperimentalFoundationApi::class)

package com.ssk.auth.presentation.di

import androidx.compose.foundation.ExperimentalFoundationApi
import com.ssk.auth.presentation.login.LoginViewModel
import com.ssk.auth.presentation.register.RegisterViewModel
import org.koin.androidx.viewmodel.dsl.viewModelOf
import org.koin.dsl.module

val authViewModel = module {
    viewModelOf(::RegisterViewModel)
    viewModelOf(::LoginViewModel)
}