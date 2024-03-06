package com.kaikeventura.httpgrpc

import com.kaikeventura.httpgrpc.entity.TaxType
import com.kaikeventura.httpgrpc.service.CompanyService
import jakarta.annotation.PostConstruct
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.stereotype.Component

@SpringBootApplication
class HttpGrpcApplication

fun main(args: Array<String>) {
	runApplication<HttpGrpcApplication>(*args)
}

@Component
class Init(
	private val companyService: CompanyService
) {

	@PostConstruct
	fun start() {
		val company = companyService.createCompany(
			companyName = "Coquinha"
		)

		companyService.addTax(
			companyId = company.id!!,
			taxType = TaxType.IOF,
			percentage = 1.5
		)

		companyService.addTax(
			companyId = company.id,
			taxType = TaxType.IPI,
			percentage = 19.2
		)

		companyService.addTax(
			companyId = company.id,
			taxType = TaxType.PIS,
			percentage = 13.4
		)
	}
}
