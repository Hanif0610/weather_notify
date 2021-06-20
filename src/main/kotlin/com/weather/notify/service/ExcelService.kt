package com.weather.notify.service

import com.weather.notify.domain.entity.Location
import com.weather.notify.domain.entity.User
import com.weather.notify.domain.repository.ExcelRepository
import com.weather.notify.domain.repository.UserRepository
import com.weather.notify.dto.CoordinateResponse
import com.weather.notify.dto.DeepRequest
import com.weather.notify.exception.CommonException
import com.weather.notify.security.AuthenticationFacade
import org.apache.commons.io.FilenameUtils
import org.apache.poi.openxml4j.opc.OPCPackage
import org.apache.poi.xssf.usermodel.XSSFCell
import org.apache.poi.xssf.usermodel.XSSFRow
import org.apache.poi.xssf.usermodel.XSSFSheet
import org.apache.poi.xssf.usermodel.XSSFWorkbook
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.stereotype.Service
import org.springframework.web.multipart.MultipartFile
import java.io.IOException

@Service
class ExcelService(
    @Autowired private val userRepository: UserRepository,
    @Autowired private val excelRepository: ExcelRepository,
    @Autowired private val authenticationFacade: AuthenticationFacade
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

    fun getCoordinate(deepRequest: DeepRequest): CoordinateResponse {
        val user: User? = userRepository.findByEmail(authenticationFacade.getEmail())
        user?: throw CommonException(404, "User Not Found.", HttpStatus.NOT_FOUND)

        val location = excelRepository.findAllByDeep1AndDeep2AndDeep3(deepRequest.deep1, deepRequest.deep2, deepRequest.deep3)
        return CoordinateResponse(
            x = location.nx,
            y = location.ny
        )
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
                        nx = nx.toString().toInt(),
                        ny = ny.toString().toInt()
                    )
                )
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }
}