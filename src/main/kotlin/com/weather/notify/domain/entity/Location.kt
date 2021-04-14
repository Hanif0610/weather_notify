package com.weather.notify.domain.entity

import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.Id
import javax.persistence.Table

@Entity
@Table(name = "location")
class Location(

    @Id
    @Column(name = "locate_id")
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