package weather.notify.weather_notify.controller

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import weather.notify.weather_notify.service.UserService

@RestController
@RequestMapping("/user")
class UserController(
    @Autowired private val userService: UserService
) {

    
}