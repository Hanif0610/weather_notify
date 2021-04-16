package com.weather.notify.service

import com.weather.notify.domain.entity.User
import com.weather.notify.domain.enums.TokenType
import com.weather.notify.domain.repository.UserRepository
import com.weather.notify.dto.LoginRequest
import com.weather.notify.dto.TokenResponse
import com.weather.notify.exception.CommonException
import com.weather.notify.security.JwtTokenProvider
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Service

@Service
class AuthService(
    @Autowired private val userRepository: UserRepository,
    @Autowired private val passwordEncoder: PasswordEncoder,
    @Autowired private val jwtTokenProvider: JwtTokenProvider
) {

    fun login(login: LoginRequest): TokenResponse {
        val user: User? = userRepository.findByEmail(login.email)
        if(!passwordEncoder.matches(login.password, user?.password)) {
            throw CommonException(400, "Password Not Match", HttpStatus.BAD_REQUEST)
        }

        return tokenResponse(user!!.email)
    }

    fun refresh(token: String): TokenResponse {
        if(!jwtTokenProvider.isRefreshToken(token)) {
            throw CommonException(401, "Invalid Token", HttpStatus.UNAUTHORIZED)
        }

        return tokenResponse(jwtTokenProvider.getUsername(token))
    }

    private fun tokenResponse(email: String): TokenResponse {
        return TokenResponse(
            accessToken = jwtTokenProvider.createToken(email, TokenType.ACCESS_TOKEN),
            refreshToken = jwtTokenProvider.createToken(email, TokenType.REFRESH_TOKEN),
            tokenType = "Bearer"
        )
    }
}