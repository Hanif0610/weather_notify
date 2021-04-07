package com.weather.notify.service

import com.weather.notify.domain.entity.User
import com.weather.notify.domain.repository.UserRepository
import com.weather.notify.dto.JoinRequest
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Service

@Service
class UserService(
    @Autowired private val userRepository: UserRepository,
    @Autowired private val passwordEncoder: PasswordEncoder
) {

    fun join(joinRequest: JoinRequest) {
        userRepository.save(
            User(
                email = joinRequest.email,
                userId = joinRequest.userId,
                password = passwordEncoder.encode(joinRequest.password)
            )
        )
    }
}