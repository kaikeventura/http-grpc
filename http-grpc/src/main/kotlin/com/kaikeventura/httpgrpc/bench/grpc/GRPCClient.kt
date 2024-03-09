package com.kaikeventura.httpgrpc.bench.grpc

import com.kaikeventura.httpgrpc.grpc.company.CompanyServiceGrpc
import com.kaikeventura.httpgrpc.grpc.company.CompanyServiceGrpc.CompanyServiceBlockingStub
import com.kaikeventura.httpgrpc.grpc.company.CompanyTaxes
import io.grpc.ManagedChannel
import io.grpc.ManagedChannelBuilder
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.stereotype.Component
import java.util.*

@Component
class GRPCClient(
    private val stub: CompanyServiceBlockingStub
) {

    fun getCompanyTaxes(companyId: UUID): CompanyTaxes.CompanyTaxesResponse? {
        val request = CompanyTaxes.GetTaxesRequest
            .newBuilder()
            .setCompanyId(companyId.toString())
            .build()

        return stub.getTaxesByCompanyId(request)

    }
}

@Configuration
class Config {
    @Bean
    fun managedChannel(): ManagedChannel =
        ManagedChannelBuilder
            .forAddress("localhost" ,9090)
            .usePlaintext()
            .build()

    @Bean
    fun companyServiceBlockingStub(): CompanyServiceBlockingStub? =
        CompanyServiceGrpc.newBlockingStub(managedChannel())
}
