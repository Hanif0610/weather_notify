package com.weather.notify.api

import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.module.kotlin.readValue
import com.weather.notify.domain.entity.User
import com.weather.notify.domain.repository.UserRepository
import com.weather.notify.dto.DataResponse
import com.weather.notify.dto.LoginRequest
import com.weather.notify.dto.TokenResponse
import org.junit.jupiter.api.AfterEach
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
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.status

@SpringBootTest
@AutoConfigureMockMvc
@TestConstructor(autowireMode = TestConstructor.AutowireMode.ALL)
class WeatherTest(
    private val mock: MockMvc,
    private val objectMapper: ObjectMapper,
    private val userRepository: UserRepository,
    private val passwordEncoder: PasswordEncoder = BCryptPasswordEncoder()
) {

    private val user = User(
        email = "marbling1293@dsm.hs.kr",
        name = "hanif",
        password = passwordEncoder.encode("123456")
    )

    private val login = LoginRequest(
        email = "marbling1293@dsm.hs.kr",
        password = "123456"
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
    @DisplayName(value = "기상청 데이터 불러오기")
    fun weather() {
        val token = getToken(post("/auth"), login)

        val response = objectMapper.readValue<MutableList<DataResponse>>(
            mock.perform(
                get("/weather")
                    .header("Authorization", "Bearer $token")
                    .param("nx", "60")
                    .param("ny", "127")
                    .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk)
                .andReturn().response.contentAsString)

        response.forEach {
            assertNotNull(it.baseDate)
            assertNotNull(it.baseTime)
            assertNotNull(it.category)
            assertNotNull(it.fcstDate)
            assertNotNull(it.fcstTime)
            assertNotNull(it.fcstValue)
        }
    }

    private fun getToken(request: MockHttpServletRequestBuilder, obj: Any? = null): String {
        return objectMapper.readValue<TokenResponse>(
            mock.perform(
                request
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(objectMapper.writeValueAsString(obj)))
                .andExpect(status().isOk)
                .andReturn().response.contentAsString).accessToken
    }
}