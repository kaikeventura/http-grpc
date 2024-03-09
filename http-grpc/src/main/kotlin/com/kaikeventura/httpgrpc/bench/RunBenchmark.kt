package com.kaikeventura.httpgrpc.bench

import com.kaikeventura.httpgrpc.bench.grpc.GRPCClient
import com.kaikeventura.httpgrpc.bench.http.HttpClient
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import java.util.*

@RestController
@RequestMapping("/benchmark")
class BenchmarkController(
    private val grpcClient: GRPCClient,
    private val httpClient: HttpClient
) {

    @GetMapping("/grpc")
    fun grpcBenchmark() {
        for (i in 1..10000) {
            grpcClient.getCompanyTaxes(UUID.fromString("48ee19f8-d1fb-47b2-a028-ab9ace0ca8c5"))
        }
    }

    @GetMapping("/http")
    fun httpBenchmark() {
        for (i in 1..10000) {
            httpClient.getCompanyTaxes(UUID.fromString("48ee19f8-d1fb-47b2-a028-ab9ace0ca8c5"))
        }
    }
}
