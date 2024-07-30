package com.ssk.core.data.networking

data class AccessTokenResponse(
    val accessToken: String,
    val expirationTimestamp: String
)