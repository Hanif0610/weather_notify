package weather.notify.weather_notify.service

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import weather.notify.weather_notify.domain.repository.UserRepository

@Service
class UserService(
    @Autowired private val userRepository: UserRepository
) {
}