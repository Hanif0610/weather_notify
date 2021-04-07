package com.weather.notify.security

import com.weather.notify.domain.entity.User
import com.weather.notify.domain.repository.UserRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.core.userdetails.UsernameNotFoundException
import org.springframework.stereotype.Service

@Service
class AuthDetailsService(
    @Autowired private val userRepository: UserRepository
): UserDetailsService {

    override fun loadUserByUsername(email: String): AuthDetails? {
        val user: User? = userRepository.findByEmail(email)
        user?: throw UsernameNotFoundException("cannot find user")

        return AuthDetails(user)
    }
}