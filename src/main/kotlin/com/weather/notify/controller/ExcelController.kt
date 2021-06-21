package com.weather.notify.controller

import com.weather.notify.dto.CoordinateResponse
import com.weather.notify.dto.DeepRequest
import com.weather.notify.service.ExcelService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.*
import org.springframework.web.multipart.MultipartFile

@RestController
@RequestMapping("/excel")
class ExcelController(
    @Autowired val excelService: ExcelService
) {

    @GetMapping("/deep1")
    fun getDeep1(): List<String> {
        return excelService.getDeep1()
    }

    @GetMapping("/deep2")
    fun getDeep2(deepRequest: DeepRequest): List<String?> {
        return excelService.getDeep2(deepRequest)
    }

    @GetMapping("/deep3")
    fun getDeep3(deepRequest: DeepRequest): List<String?> {
        return excelService.getDeep3(deepRequest)
    }

    @GetMapping("/coordinate")
    fun getCoordinate(deepRequest: DeepRequest): CoordinateResponse {
        return excelService.getCoordinate(deepRequest)
    }

    @PostMapping("/upload")
    fun uploadExcel(@RequestParam("excel") excel: MultipartFile) {
        excelService.uploadExcel(excel)
    }
}