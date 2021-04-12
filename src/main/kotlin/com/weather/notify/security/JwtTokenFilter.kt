package com.weather.notify.security

import io.jsonwebtoken.ExpiredJwtException
import io.jsonwebtoken.MalformedJwtException
import io.jsonwebtoken.SignatureException
import org.springframework.security.core.Authentication
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.web.filter.OncePerRequestFilter
import javax.servlet.FilterChain
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

class JwtTokenFilter(
    private val jwtTokenProvider: JwtTokenProvider
) : OncePerRequestFilter() {

    override fun doFilterInternal(
        request: HttpServletRequest,
        response: HttpServletResponse,
        filterChain: FilterChain
    ) {
        try {
            val token: String? = jwtTokenProvider.resolveToken(request)
            if(token != null && jwtTokenProvider.validateToken(token)) {
                val auth: Authentication = jwtTokenProvider.getAuthentication(token)
                SecurityContextHolder.getContext().authentication = auth
            }
            filterChain.doFilter(request, response)
        } catch (e: Exception) {
            when(e) {
                is SignatureException, is MalformedJwtException -> {
                    response.sendError(401, "SignatureException error")
                }
                is ExpiredJwtException -> {
                    response.sendError(403, "ExpiredJwtException error")
                }
                else -> {
                    SecurityContextHolder.clearContext()
                }
            }
        }
    }
}