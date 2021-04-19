package com.weather.notify.service

import com.weather.notify.domain.entity.User
import com.weather.notify.domain.repository.UserRepository
import com.weather.notify.dto.JoinRequest
import com.weather.notify.dto.ProfileResponse
import com.weather.notify.dto.UpdateNameRequest
import com.weather.notify.exception.CommonException
import com.weather.notify.security.AuthenticationFacade
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Service

@Service
class UserService(
    @Autowired private val userRepository: UserRepository,
    @Autowired private val passwordEncoder: PasswordEncoder,
    @Autowired private val authenticationFacade: AuthenticationFacade
) {

    fun profile(): ProfileResponse {
        val user: User? = userRepository.findByEmail(authenticationFacade.getEmail())
        user?: throw CommonException(404, "User Not Found.", HttpStatus.NOT_FOUND)

        return ProfileResponse(
            email = user.email,
            name = user.name
        )
    }

    fun join(joinRequest: JoinRequest) {
        if(userRepository.existsByEmail(joinRequest.email)) {
            throw CommonException(409, "Email already exists.", HttpStatus.CONFLICT)
        }

        userRepository.save(
            User(
                email = joinRequest.email,
                name = joinRequest.name,
                password = passwordEncoder.encode(joinRequest.password)
            )
        )
    }

    fun updateName(updateNameRequest: UpdateNameRequest) {
        val user: User? = userRepository.findByEmail(authenticationFacade.getEmail())
        user?: throw CommonException(404, "User Not Found.", HttpStatus.NOT_FOUND)

        userRepository.save(user.updateName(updateNameRequest))
    }
}