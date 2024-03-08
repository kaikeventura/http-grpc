package com.kaikeventura.httpgrpc

import com.kaikeventura.httpgrpc.entity.TaxType
import com.kaikeventura.httpgrpc.service.CompanyService
import io.grpc.BindableService
import io.grpc.ServerBuilder
import jakarta.annotation.PostConstruct
import org.springframework.boot.ApplicationArguments
import org.springframework.boot.ApplicationRunner
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.stereotype.Component

@SpringBootApplication
class HttpGrpcApplication(val services: List<BindableService>) : ApplicationRunner {
	override fun run(args: ApplicationArguments?) {
		val serverBuilder = ServerBuilder.forPort(9090)
		services.forEach{
			serverBuilder.addService(it)
		}
		serverBuilder
			.build()
			.start()
	}
}

fun main(args: Array<String>) {
	runApplication<HttpGrpcApplication>(*args)
}

@Component
class Init(
	private val companyService: CompanyService
) {

	//@PostConstruct
	fun start() {
		for (i in 1..10000) {
			val company = companyService.createCompany(
				companyName = "Coquinha $i"
			)

			companyService.addTax(
				companyId = company.id!!,
				taxType = TaxType.IOF,
				percentage = 0.02 * i
			)

			companyService.addTax(
				companyId = company.id,
				taxType = TaxType.IPI,
				percentage = 0.2 * i
			)

			companyService.addTax(
				companyId = company.id,
				taxType = TaxType.PIS,
				percentage = 0.3 * i
			)
		}
	}
}
