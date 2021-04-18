package com.weather.notify.security

import org.springframework.security.core.Authentication
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.stereotype.Component

@Component
class AuthenticationFacade {

    fun getAuthentication(): Authentication {
        return SecurityContextHolder.getContext().authentication
    }

    fun getEmail(): String {
        return this.getAuthentication().name
    }
}