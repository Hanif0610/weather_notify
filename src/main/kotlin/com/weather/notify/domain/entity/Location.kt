package com.weather.notify.domain.entity

import org.springframework.data.mongodb.core.mapping.Document
import org.springframework.data.mongodb.core.mapping.Field
import javax.persistence.Id

@Document(value = "location")
class Location(

    @Id
    @Field(name = "location_id")
    var id: String,

    @Field(name = "deep1")
    val deep1: String,

    @Field(name = "deep2")
    val deep2: String? = null,

    @Field(name = "deep3")
    val deep3: String? = null,

    @Field(name = "nx")
    val nx: Double,

    @Field(name = "ny")
    val ny: Double
)