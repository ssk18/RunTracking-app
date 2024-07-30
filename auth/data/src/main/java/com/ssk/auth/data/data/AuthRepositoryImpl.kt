package com.ssk.auth.data.data

import com.ssk.auth.domain.domain.AuthRepository
import com.ssk.core.data.networking.post
import com.ssk.core.domain.AuthInfo
import com.ssk.core.domain.SessionStorage
import com.ssk.core.domain.util.DataError
import com.ssk.core.domain.util.EmptyDataResult
import com.ssk.core.domain.util.asEmptyDataResult
import io.ktor.client.HttpClient
import com.ssk.core.domain.util.Result

class AuthRepositoryImpl(
    private val httpClient: HttpClient,
    private val sessionStorage: SessionStorage
) : AuthRepository {
    override suspend fun register(
        email: String,
        password: String
    ): EmptyDataResult<DataError.Network> {
        return httpClient.post<RegisterRequest, Unit>(
            route = "/register",
            body = RegisterRequest(
                email, password
            )
        )
    }

    override suspend fun login(
        email: String,
        password: String
    ): EmptyDataResult<DataError.Network> {
        val result = httpClient.post<LoginRequest, LoginResponse>(
            route = "/login",
            body = LoginRequest(
                email, password
            )
        )
        if (result is Result.Success) {
            sessionStorage.set(
                info = AuthInfo(
                    accessToken = result.data.accessToken,
                    refreshToken = result.data.refreshToken,
                    userId = result.data.userId
                )
            )
        }
        return result.asEmptyDataResult()
    }
}