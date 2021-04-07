package com.weather.notify.domain.entity

import org.springframework.data.mongodb.core.mapping.Document
import javax.persistence.Id

@Document("locations")
class Location(

    @Id
    var id: String,
    val deep1: String,
    val deep2: String? = null,
    val deep3: String? = null,
    val nx: Double,
    val ny: Double
)