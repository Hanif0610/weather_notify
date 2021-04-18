package com.weather.notify.user

import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.module.kotlin.readValue
import com.weather.notify.domain.entity.User
import com.weather.notify.domain.repository.UserRepository
import com.weather.notify.dto.JoinRequest
import com.weather.notify.dto.LoginRequest
import com.weather.notify.dto.ProfileResponse
import com.weather.notify.dto.TokenResponse
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.Assertions.assertNotNull
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.http.MediaType
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.test.context.TestConstructor
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.status

@SpringBootTest
@AutoConfigureMockMvc
@TestConstructor(autowireMode = TestConstructor.AutowireMode.ALL)
class UserTest(
    private val mock: MockMvc,
    private val objectMapper: ObjectMapper,
    private val userRepository: UserRepository,
    private val passwordEncoder: PasswordEncoder = BCryptPasswordEncoder()
) {

    @AfterEach
    fun clean() {
        userRepository.deleteAll()
    }

    @Test
    @DisplayName(value = "유저 추가")
    fun join() {
        val user = JoinRequest(
            email = "marbling1293@dsm.hs.kr",
            name = "hanif",
            password = "123456"
        )

        mock.perform(post("/user")
            .contentType(MediaType.APPLICATION_JSON)
            .content(objectMapper.writeValueAsString(user)))
            .andExpect(status().isOk)

        userRepository.findByEmail(user.email).let {
            assertNotNull(it?.email)
            assertNotNull(it?.name)
            assertNotNull(it?.password)
        }
    }
}