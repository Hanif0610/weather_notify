package com.weather.notify.domain.repository

import com.weather.notify.domain.entity.User
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface UserRepository: JpaRepository<User, Int> {
    fun findByEmail(email: String): User?
}