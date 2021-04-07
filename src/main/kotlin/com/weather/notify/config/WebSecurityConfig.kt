package com.weather.notify.config

import com.weather.notify.security.AuthDetailsService
import com.weather.notify.security.JwtConfigurer
import com.weather.notify.security.JwtTokenProvider
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter
import org.springframework.security.crypto.password.PasswordEncoder

@Configuration
@EnableWebSecurity
class WebSecurityConfig(
    @Autowired private val passwordEncoder: PasswordEncoder,
    @Autowired private val jwtTokenProvider: JwtTokenProvider,
    @Autowired private val authDetailsService: AuthDetailsService
): WebSecurityConfigurerAdapter() {

    override fun configure(auth: AuthenticationManagerBuilder) {
        auth.userDetailsService(authDetailsService)
            .passwordEncoder(passwordEncoder)
    }

    override fun configure(http: HttpSecurity?) {
        http
            ?.csrf()?.disable()
            ?.formLogin()?.disable()
            ?.headers()?.frameOptions()?.disable()
            ?.and()
            ?.authorizeRequests()
                ?.antMatchers("/user/**")?.permitAll()
                ?.antMatchers("/excel/**")?.permitAll()
                ?.antMatchers("/weather/**")?.permitAll()
                ?.anyRequest()?.authenticated()
            ?.and()
            ?.apply(JwtConfigurer(jwtTokenProvider))
    }

    @Bean
    override fun authenticationManagerBean(): AuthenticationManager {
        return super.authenticationManagerBean()
    }
}