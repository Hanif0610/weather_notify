package weather.notify.weather_notify.controller

import weather.notify.weather_notify.dto.WeatherRequest
import weather.notify.weather_notify.service.WeatherService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/weather")
class WeatherController(
    @Autowired private val weatherService: WeatherService
) {

    @GetMapping
    fun getData(weather: WeatherRequest): Any? {
        return weatherService.getData(weather)
    }
}