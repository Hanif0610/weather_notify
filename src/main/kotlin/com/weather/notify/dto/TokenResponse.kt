package com.weather.notify.dto

class TokenResponse(
    val accessToken: String,
    val refreshToken: String,
    val tokenType: String
)