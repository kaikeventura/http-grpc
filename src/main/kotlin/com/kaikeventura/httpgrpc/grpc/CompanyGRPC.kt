package com.kaikeventura.httpgrpc.grpc

import com.kaikeventura.httpgrpc.grpc.company.CompanyServiceGrpc
import com.kaikeventura.httpgrpc.grpc.company.CompanyTaxes
import com.kaikeventura.httpgrpc.grpc.company.CompanyTaxes.CompanyTaxesResponse
import com.kaikeventura.httpgrpc.grpc.company.CompanyTaxes.Tax
import com.kaikeventura.httpgrpc.service.CompanyService
import io.grpc.stub.StreamObserver
import net.devh.boot.grpc.server.service.GrpcService
import java.util.UUID

@GrpcService
class CompanyGRPC(
    private val companyService: CompanyService
) : CompanyServiceGrpc.CompanyServiceImplBase() {

    override fun getTaxesByCompanyId(
        request: CompanyTaxes.GetTaxesRequest?,
        responseObserver: StreamObserver<CompanyTaxesResponse>?
    ) {
        val companyTaxes = companyService.getCompanyTaxes(UUID.fromString(request!!.companyId))

        val responseTaxes = companyTaxes.taxes.map {
            Tax.newBuilder()
                .setType(it.type.name)
                .setPercentage(it.percentage)
                .build()
        }

        val responseCompany = CompanyTaxesResponse
            .newBuilder()
            .setId(companyTaxes.id.toString())
            .addAllTaxes(responseTaxes)
            .build()

        responseObserver?.onNext(responseCompany)
        responseObserver?.onCompleted()
    }
}
