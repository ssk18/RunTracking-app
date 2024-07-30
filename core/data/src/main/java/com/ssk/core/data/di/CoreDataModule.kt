package com.ssk.core.data.di

import com.ssk.core.data.auth.EncryptedSessionStorage
import com.ssk.core.data.networking.HttpClientFactory
import com.ssk.core.domain.SessionStorage
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.bind
import org.koin.dsl.module

val coreDataModule = module {
    single {
        HttpClientFactory(get()).build()
    }

    singleOf(::EncryptedSessionStorage).bind<SessionStorage>()
}