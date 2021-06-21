package com.weather.notify.controller

import com.weather.notify.dto.DataResponse
import com.weather.notify.dto.WeatherRequest
import com.weather.notify.service.WeatherService
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
    fun getData(weather: WeatherRequest): MutableList<DataResponse>? {
        return weatherService.getData(weather)
    }
}