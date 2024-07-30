package com.ssk.auth.domain.domain

import com.ssk.core.domain.util.DataError
import com.ssk.core.domain.util.EmptyDataResult

interface AuthRepository {
    suspend fun register(email: String, password: String): EmptyDataResult<DataError.Network>
    suspend fun login(email: String, password: String): EmptyDataResult<DataError.Network>
}