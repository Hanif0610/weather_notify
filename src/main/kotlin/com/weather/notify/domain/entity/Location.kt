package com.weather.notify.domain.entity

import org.springframework.data.mongodb.core.mapping.Document
import javax.persistence.Column
import javax.persistence.Id

@Document(value = "location")
class Location(

    @Id
    var id: String,

    @Column(name = "deep1")
    val deep1: String,

    @Column(name = "deep2")
    val deep2: String? = null,

    @Column(name = "deep3")
    val deep3: String? = null,

    @Column(name = "nx")
    val nx: Double,

    @Column(name = "ny")
    val ny: Double
)