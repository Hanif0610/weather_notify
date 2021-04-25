package com.weather.notify.service

import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.module.kotlin.KotlinModule
import com.weather.notify.domain.entity.User
import com.weather.notify.domain.repository.UserRepository
import com.weather.notify.dto.DataResponse
import com.weather.notify.dto.WeatherRequest
import com.weather.notify.exception.CommonException
import com.weather.notify.security.AuthenticationFacade
import okhttp3.OkHttpClient
import okhttp3.Request
import org.json.simple.JSONArray
import org.json.simple.JSONObject
import org.json.simple.parser.JSONParser
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.stereotype.Service
import java.time.LocalDate
import java.time.LocalDateTime

@Service
class WeatherService(
    @Autowired private val userRepository: UserRepository,
    @Autowired private val authenticationFacade: AuthenticationFacade
){

    fun getData(weather: WeatherRequest): Any? {
        val user: User? = userRepository.findByEmail(authenticationFacade.getEmail())
        user?: throw CommonException(404, "User Not Found.", HttpStatus.NOT_FOUND)

        val date: String = if("${LocalDateTime.now().hour}00".toInt() >= 2000) {
            LocalDate.now().toString().split("-").joinToString("")
        } else {
            LocalDate.now().minusDays(1).toString().split("-").joinToString("")
        }

        val key = "%2FQ%2Bg4KpLm7WVsvFUoYpDvdN7enbMborLyCooFHPNt03lmWFK9OZycaqh5Z%2BcrgN1MJSzsV8TxI9BQMJEQ6LcBw%3D%3D"

        val client = OkHttpClient()
        val request = Request.Builder()
            .url("http://apis.data.go.kr/1360000/VilageFcstInfoService/getVilageFcst?" +
                    "serviceKey=$key&numOfRows=93&pageNo=1&dataType=JSON&" +
                    "base_date=$date&base_time=2000&nx=${weather.nx}&ny=${weather.ny}").build()
        val response = client.newCall(request).execute()

        if(response.isSuccessful) {
            val body = response.body!!.string()
            val objectMapper = ObjectMapper().registerModule(KotlinModule())

            val jsonParser = JSONParser()
            val jsonObject: JSONObject = jsonParser.parse(body) as JSONObject
            val jsonResponse: JSONObject = jsonObject["response"] as JSONObject
            val jsonBody: JSONObject = jsonResponse["body"] as JSONObject
            val items: JSONObject = jsonBody["items"] as JSONObject
            val item: JSONArray = items["item"] as JSONArray

            val list: MutableList<DataResponse> = ArrayList()
            item.forEach {
                list.add(objectMapper.readValue(it.toString(), DataResponse::class.java))
            }

            return list
        }

        return ""
    }
}