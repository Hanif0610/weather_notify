package com.weather.notify.exception

import org.springframework.http.HttpStatus

class CommonException(
    val code: Int,
    message: String,
    val status: HttpStatus
): RuntimeException(message)