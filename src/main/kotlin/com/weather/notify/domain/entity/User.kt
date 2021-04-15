package com.weather.notify.domain.entity

import org.springframework.data.mongodb.core.mapping.Document
import org.springframework.data.mongodb.core.mapping.Field
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id

@Document(value = "user")
class User(

    @Id
    @Field(name = "user_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    internal val id: Long? = null,

    @Field(name = "email")
    internal val email: String,

    @Field(name = "name")
    internal var name: String,

    @Field(name = "password")
    internal var password: String
)