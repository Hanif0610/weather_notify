package weather.notify.weather_notify.domain.repository

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import weather.notify.weather_notify.domain.entity.User

@Repository
interface UserRepository: JpaRepository<User, String>