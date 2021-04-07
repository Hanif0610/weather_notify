package weather.notify.weather_notify.domain.entity

import javax.persistence.Entity
import javax.persistence.Id

@Entity
class User(

    @Id
    internal val email: String,
    internal var userId: String,
    internal var password: String
)