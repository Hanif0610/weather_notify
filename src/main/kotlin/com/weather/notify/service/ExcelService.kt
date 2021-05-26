package com.weather.notify.service

import com.weather.notify.domain.entity.Location
import com.weather.notify.domain.repository.ExcelRepository
import com.weather.notify.dto.DeepRequest
import org.apache.commons.io.FilenameUtils
import org.apache.poi.openxml4j.opc.OPCPackage
import org.apache.poi.xssf.usermodel.XSSFCell
import org.apache.poi.xssf.usermodel.XSSFRow
import org.apache.poi.xssf.usermodel.XSSFSheet
import org.apache.poi.xssf.usermodel.XSSFWorkbook
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import org.springframework.web.multipart.MultipartFile
import java.io.IOException

@Service
class ExcelService(
    @Autowired private val excelRepository: ExcelRepository
) {

    fun getDeep1(): List<String> {
        return excelRepository.findAll().map { it.deep1 }.distinct()
    }

    fun getDeep2(deepRequest: DeepRequest): List<String?> {
        return excelRepository.findAllByDeep1(deepRequest.deep1).filter { it.deep2 != "" }.map { it.deep2 }.distinct()
    }

    fun getDeep3(deepRequest: DeepRequest): List<String> {
        return excelRepository.findAllByDeep1AndDeep2(deepRequest.deep1, deepRequest.deep2)
            .filter { it.deep3 != "" }.map { it.deep3!! }
    }

    fun uploadExcel(excel: MultipartFile) {
        val extension = FilenameUtils.getExtension(excel.originalFilename)
        if (extension != "xlsx" && extension != "xls") throw IOException("엑셀파일만 업로드 해주세요.")

        try {
            val opcPackage: OPCPackage = OPCPackage.open(excel.inputStream)
            val workbook = XSSFWorkbook(opcPackage)

            val sheet: XSSFSheet = workbook.getSheetAt(0)

            for(i in 1 .. sheet.lastRowNum - 2) {
                val row: XSSFRow = sheet.getRow(i) ?: continue

                val code: XSSFCell = row.getCell(1)
                val deep1: XSSFCell = row.getCell(2)
                val deep2: XSSFCell? = row.getCell(3)
                val deep3: XSSFCell? = row.getCell(4)
                val nx: XSSFCell? = row.getCell(5)
                val ny: XSSFCell = row.getCell(6)

                excelRepository.save(
                    Location(
                        id = code.toString(),
                        deep1 = deep1.toString(),
                        deep2 = deep2?.toString(),
                        deep3 = deep3?.toString(),
                        nx = nx.toString().toDouble(),
                        ny = ny.toString().toDouble()
                    )
                )
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }
}