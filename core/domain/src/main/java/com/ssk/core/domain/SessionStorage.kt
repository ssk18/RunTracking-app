package com.ssk.core.domain

import com.ssk.core.domain.AuthInfo

interface SessionStorage {
    suspend fun get(): AuthInfo?
    suspend fun set(info: AuthInfo?)
}