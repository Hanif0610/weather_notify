package com.weather.notify.domain.repository

import com.weather.notify.domain.entity.Location
import org.springframework.data.mongodb.repository.MongoRepository
import org.springframework.stereotype.Repository

@Repository
interface ExcelRepository: MongoRepository<Location, String>