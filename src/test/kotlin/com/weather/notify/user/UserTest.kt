package com.weather.notify.user

import com.weather.notify.domain.entity.User
import com.weather.notify.domain.repository.UserRepository
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertNotNull
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest
import org.springframework.test.context.TestConstructor
import org.springframework.test.context.junit.jupiter.SpringExtension

@DataMongoTest
@ExtendWith(SpringExtension::class)
@TestConstructor(autowireMode = TestConstructor.AutowireMode.ALL)
class UserTest(
    private val userRepository: UserRepository
) {

    private val email: String = "marbling1293@dsm.hs.kr"
    private val name: String = "hanif"
    private val password: String = "123456"

    @AfterEach
    fun clean() {
        userRepository.deleteAll()
    }

    @Test
    @DisplayName(value = "유저 추가")
    fun join() {
        val user: User = userRepository.save(
            User(
                email = email,
                name = name,
                password = password
            )
        )

        assertNotNull(user.email)
        assertNotNull(user.name)
        assertNotNull(user.password)
        assertEquals(user.email, email)
    }
}