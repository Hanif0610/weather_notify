package alone.project.weather_notify.domain.repository

import alone.project.weather_notify.domain.entity.Location
import org.springframework.data.mongodb.repository.MongoRepository
import org.springframework.stereotype.Repository

@Repository
interface ExcelRepository: MongoRepository<Location, String>