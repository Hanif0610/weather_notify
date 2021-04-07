package weather.notify.controller

import weather.notify.domain.entity.Location
import weather.notify.service.ExcelService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.*
import org.springframework.web.multipart.MultipartFile

@RestController
@RequestMapping("/excel")
class ExcelController(
    @Autowired val excelService: ExcelService
) {

    @PostMapping("/upload")
    fun uploadExcel(@RequestParam("excel") excel: MultipartFile) {
        excelService.uploadExcel(excel)
    }
}