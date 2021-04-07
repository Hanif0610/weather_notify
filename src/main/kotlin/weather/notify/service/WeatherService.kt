package weather.notify.service

import weather.notify.dto.DataResponse
import weather.notify.dto.WeatherRequest
import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.module.kotlin.KotlinModule
import okhttp3.OkHttpClient
import okhttp3.Request
import org.json.simple.JSONArray
import org.json.simple.JSONObject
import org.json.simple.parser.JSONParser
import org.springframework.stereotype.Service
import java.time.LocalDate

@Service
class WeatherService{

    fun getData(weather: WeatherRequest): Any? {
        val date: String = LocalDate.now().minusDays(1).toString().split("-").joinToString("")

        val key = "egDfenHknpZmGHHRQMji%2B9Qx99SYWf9xPzOkmz%2BErohCIFA8YI%2B1XNNKWikdd7hmQ9d8TIY3QAUWiN2zuHInCg%3D%3D"

        val client = OkHttpClient()
        val request = Request.Builder()
            .url("http://apis.data.go.kr/1360000/VilageFcstInfoService/getVilageFcst?" +
                    "serviceKey=$key&numOfRows=93&dataType=JSON&" +
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