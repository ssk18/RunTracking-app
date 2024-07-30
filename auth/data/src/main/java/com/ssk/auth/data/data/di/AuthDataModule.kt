package com.ssk.auth.data.data.di

import com.ssk.auth.data.data.AuthRepositoryImpl
import com.ssk.auth.data.data.EmailPatternValidator
import com.ssk.auth.domain.domain.AuthRepository
import com.ssk.auth.domain.domain.PatternValidator
import com.ssk.auth.domain.domain.UserDataValidator
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.bind
import org.koin.dsl.module

val authDataModule = module {
    single<PatternValidator> {
        EmailPatternValidator
    }
    singleOf(::UserDataValidator)
    singleOf(::AuthRepositoryImpl).bind<AuthRepository>()
}