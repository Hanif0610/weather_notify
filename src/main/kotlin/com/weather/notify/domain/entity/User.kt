package com.weather.notify.domain.entity

import org.springframework.data.mongodb.core.mapping.Document
import javax.persistence.Column
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id

@Document(value = "user")
class User(

    @Id
    @Column(name = "user_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    internal val id: Long? = null,

    @Column(name = "email")
    internal val email: String,

    @Column(name = "name")
    internal var name: String,

    @Column(name = "password")
    internal var password: String
)