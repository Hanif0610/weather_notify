package com.weather.notify.domain.entity

import org.springframework.data.mongodb.core.mapping.Document
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id

@Document("user")
class User(

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    internal val id: Long? = null,
    internal val email: String,
    internal var userId: String,
    internal var password: String
)