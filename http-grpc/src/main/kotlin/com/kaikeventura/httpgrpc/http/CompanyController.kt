package com.kaikeventura.httpgrpc.http

import com.kaikeventura.httpgrpc.service.CompanyService
import com.kaikeventura.httpgrpc.service.CompanyTaxes
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.RestController
import java.util.UUID

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
