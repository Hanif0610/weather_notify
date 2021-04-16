package com.weather.notify.auth

import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.module.kotlin.readValue
import com.weather.notify.domain.entity.User
import com.weather.notify.domain.repository.UserRepository
import com.weather.notify.dto.LoginRequest
import com.weather.notify.dto.TokenResponse
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertNotNull
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.http.MediaType
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.test.context.TestConstructor
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders
import org.springframework.test.web.servlet.result.MockMvcResultMatchers

@SpringBootTest
@AutoConfigureMockMvc
@TestConstructor(autowireMode = TestConstructor.AutowireMode.ALL)
class AuthTest(
    private var mock: MockMvc,
    private val objectMapper: ObjectMapper,
    private val userRepository: UserRepository,
    private val passwordEncoder: PasswordEncoder = BCryptPasswordEncoder()
) {

    private val user = User(
        email = "marbling1293@dsm.hs.kr",
        name = "hanif",
        password = passwordEncoder.encode("123456")
    )

    @BeforeEach
    fun init() {
        userRepository.save(user)
    }

    @AfterEach
    fun clean() {
        userRepository.deleteAll()
    }

    @Test
    @DisplayName(value = "로그인")
    fun login() {
        val login = LoginRequest(
            email = "marbling1293@dsm.hs.kr",
            password = "123456"
        )

        val response = objectMapper.readValue<TokenResponse>(
            mock.perform(
                MockMvcRequestBuilders.post("/auth")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(objectMapper.writeValueAsString(login)))
                .andExpect(MockMvcResultMatchers.status().isOk)
                .andReturn().response.contentAsString
        )

        assertNotNull(response.accessToken)
        assertNotNull(response.refreshToken)
        assertEquals(response.tokenType, "Bearer")
    }
}