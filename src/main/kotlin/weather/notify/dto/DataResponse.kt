package weather.notify.dto

class DataResponse(

    val baseDate: String,
    val fcstTime: String,
    val fcstValue: String,
    val nx: Int,
    val ny: Int,
    val category: String,
    val baseTime: String,
    val fcstDate: String
)