package weather.notify

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class WeatherNotifyApplication

fun main(args: Array<String>) {
    runApplication<WeatherNotifyApplication>(*args)
}
