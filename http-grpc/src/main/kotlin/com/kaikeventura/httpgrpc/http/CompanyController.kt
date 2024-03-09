package com.kaikeventura.httpgrpc.http

import com.kaikeventura.httpgrpc.service.CompanyService
import com.kaikeventura.httpgrpc.service.CompanyTaxes
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.*
import java.util.*

@RestController
@RequestMapping("/company")
class CompanyController(
    private val companyService: CompanyService
) {

    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/{companyId}/taxes")
    fun getCompanyTaxes(@PathVariable companyId: UUID): CompanyTaxes =
        companyService.getCompanyTaxes(companyId)
}
