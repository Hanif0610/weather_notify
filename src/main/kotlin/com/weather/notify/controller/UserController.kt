package com.weather.notify.controller

import com.weather.notify.dto.JoinRequest
import com.weather.notify.dto.ProfileResponse
import com.weather.notify.dto.UpdateNameRequest
import com.weather.notify.dto.UpdatePasswordRequest
import com.weather.notify.service.UserService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.validation.annotation.Validated
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/user")
class UserController (
    @Autowired private val userService: UserService
) {

    @GetMapping
    fun profile(): ProfileResponse {
        return userService.profile()
    }

    @PostMapping
    fun join(@RequestBody @Validated joinRequest: JoinRequest) {
        userService.join(joinRequest)
    }

    @PutMapping("/name")
    fun updateName(@RequestBody @Validated updateNameRequest: UpdateNameRequest) {
        userService.updateName(updateNameRequest)
    }

    @PutMapping("/password")
    fun updatePassword(@RequestBody @Validated updatePasswordRequest: UpdatePasswordRequest) {
        userService.updatePassword(updatePasswordRequest)
    }

    @DeleteMapping
    fun deleteUser() {
        userService.deleteUser()
    }
}