package com.weather.notify.security

import com.weather.notify.domain.entity.User
import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.userdetails.UserDetails

class AuthDetails(
    private val user: User
): UserDetails {
    override fun getAuthorities(): MutableCollection<out GrantedAuthority> = ArrayList()

    override fun getPassword(): String = user.password

    override fun getUsername(): String = user.email

    override fun isAccountNonExpired(): Boolean = true

    override fun isAccountNonLocked(): Boolean = true

    override fun isCredentialsNonExpired(): Boolean = true

    override fun isEnabled(): Boolean = true
}