package com.weather.notify.controller

import com.weather.notify.dto.LoginRequest
import com.weather.notify.dto.TokenResponse
import com.weather.notify.service.AuthService
import org.springframework.validation.annotation.Validated
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/auth")
class AuthController(
    private val authService: AuthService
) {

    @PostMapping
    fun login(@RequestBody @Validated loginRequest: LoginRequest): TokenResponse {
        return authService.login(loginRequest)
    }

    @PutMapping
    fun refresh(@RequestHeader("X-Refresh-Token") token: String): TokenResponse {
        return authService.refresh(token)
    }
}