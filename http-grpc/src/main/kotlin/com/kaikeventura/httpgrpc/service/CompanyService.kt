package com.kaikeventura.httpgrpc.service

import com.kaikeventura.httpgrpc.entity.CompanyEntity
import com.kaikeventura.httpgrpc.entity.TaxEntity
import com.kaikeventura.httpgrpc.entity.TaxType
import com.kaikeventura.httpgrpc.repository.CompanyRepository
import com.kaikeventura.httpgrpc.repository.TaxRepository
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service
import java.util.UUID

@Service
class CompanyService(
    private val companyRepository: CompanyRepository,
    private val taxRepository: TaxRepository
) {
    fun createCompany(companyName: String): CompanyEntity =
        companyRepository.save(
            CompanyEntity(
                name = companyName
            )
        )

    fun addTax(
        companyId: UUID,
        taxType: TaxType,
        percentage: Double
    ) {
        val company = companyRepository.findByIdOrNull(companyId)
            ?: throw RuntimeException("Company $companyId not found")

        taxRepository.save(
            TaxEntity(
                type = taxType,
                percentage = percentage,
                company = company
            )
        )
    }

    fun getCompanyTaxes(companyId: UUID): CompanyTaxes {
        val taxes = taxRepository.findAllByCompanyId(companyId)

        return CompanyTaxes(
            id = companyId,
            taxes = taxes.map {
                Tax(
                    type = it.type,
                    percentage = it.percentage
                )
            }
        )
    }
}

data class CompanyTaxes(
    val id: UUID,
    val taxes: List<Tax>
)

data class Tax(
    val type: TaxType,
    val percentage: Double
)
