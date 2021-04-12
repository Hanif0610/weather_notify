package com.weather.notify.exception.error

import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import com.weather.notify.exception.CommonExceptionResponse
import org.springframework.security.core.AuthenticationException
import org.springframework.security.web.AuthenticationEntryPoint
import org.springframework.stereotype.Component
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

@Component
class InvalidTokenEntryPoint: AuthenticationEntryPoint {

    override fun commence(
        request: HttpServletRequest?,
        response: HttpServletResponse?,
        authException: AuthenticationException?
    ) {

        val objectMapper = jacksonObjectMapper().findAndRegisterModules()
        val exceptionResponse = objectMapper.writeValueAsString(
            CommonExceptionResponse(
                code = 401,
                message = "Invalid Token",
            )
        )

        response?.contentType = "application/json"
        response?.status = HttpServletResponse.SC_UNAUTHORIZED
        response?.outputStream?.println(exceptionResponse)
    }
}