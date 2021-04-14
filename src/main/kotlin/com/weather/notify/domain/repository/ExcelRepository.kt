package com.weather.notify.domain.repository

import com.weather.notify.domain.entity.Location
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface ExcelRepository: JpaRepository<Location, String>