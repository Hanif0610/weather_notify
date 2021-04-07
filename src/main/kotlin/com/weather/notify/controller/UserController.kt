package com.weather.notify.controller

import com.weather.notify.dto.JoinRequest
import com.weather.notify.service.UserService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.validation.annotation.Validated
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/user")
class UserController (
    @Autowired private val userService: UserService
) {

    @PostMapping
    fun join(@RequestBody @Validated joinRequest: JoinRequest) {
        userService.join(joinRequest)
    }
}