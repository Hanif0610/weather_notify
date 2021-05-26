package com.weather.notify.controller

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
    fun getDeep2(@RequestParam("deep1") deep1: String): List<String?> {
        return excelService.getDeep2(deep1)
    }

    @GetMapping("/deep3")
    fun getDeep3(@RequestParam("deep1") deep1: String,
                 @RequestParam("deep2") deep2: String): List<String?> {
        return excelService.getDeep3(deep1, deep2)
    }

    @PostMapping("/upload")
    fun uploadExcel(@RequestParam("excel") excel: MultipartFile) {
        excelService.uploadExcel(excel)
    }
}