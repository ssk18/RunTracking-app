package com.ssk.core.data.auth

import com.ssk.core.domain.AuthInfo

fun AuthInfo.toAuthInfoSerializable() =
        AuthInfoSerializable(
            accessToken, refreshToken, userId
        )

fun AuthInfoSerializable.toAuthInfo() =
    AuthInfo(
        accessToken, refreshToken, userId
    )