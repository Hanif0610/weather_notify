package com.weather.notify

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import springfox.documentation.swagger2.annotations.EnableSwagger2

@EnableSwagger2
@SpringBootApplication
class WeatherNotifyApplication

fun main(args: Array<String>) {
    runApplication<WeatherNotifyApplication>(*args)
}
