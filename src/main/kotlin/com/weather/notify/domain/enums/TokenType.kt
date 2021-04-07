package com.weather.notify.domain.enums

enum class TokenType(val millisecond: Long) {
    ACCESS_TOKEN(1000 * 60 * 60 * 4),
    REFRESH_TOKEN(1000 * 60 * 60 * 24 * 7)
}