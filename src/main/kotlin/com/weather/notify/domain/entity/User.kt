package com.weather.notify.domain.entity

import javax.persistence.*

@Entity
@Table(name = "user")
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