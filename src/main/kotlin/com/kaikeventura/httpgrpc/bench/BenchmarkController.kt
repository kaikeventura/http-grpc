package com.kaikeventura.httpgrpc.bench

import com.kaikeventura.httpgrpc.bench.grpc.GRPCClient
import com.kaikeventura.httpgrpc.bench.http.HttpClient
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import java.util.*
import java.util.stream.IntStream

@RestController
@RequestMapping("/benchmark")
class BenchmarkController(
    private val grpcClient: GRPCClient,
    private val httpClient: HttpClient
) {

    @GetMapping("/grpc/sync")
    fun grpcSyncBenchmark() {
        val init = System.currentTimeMillis()
        IntStream.range(1, 5000).forEach {
            grpcClient.getCompanyTaxes(UUID.fromString("48ee19f8-d1fb-47b2-a028-ab9ace0ca8c5"))
        }
        val finished = System.currentTimeMillis()
        println("GRPC sync: ${finished-init} millis")
    }

    @GetMapping("/grpc/async")
    fun grpcAsyncBenchmark() {
        val init = System.currentTimeMillis()
        IntStream.range(1, 5000).parallel().forEach {
            grpcClient.getCompanyTaxes(UUID.fromString("48ee19f8-d1fb-47b2-a028-ab9ace0ca8c5"))
        }
        val finished = System.currentTimeMillis()
        println("GRPC async: ${finished-init} millis")
    }

    @GetMapping("/http/sync")
    fun httpBenchmark() {
        val init = System.currentTimeMillis()
        IntStream.range(1, 5000).forEach {
            httpClient.getCompanyTaxes(UUID.fromString("48ee19f8-d1fb-47b2-a028-ab9ace0ca8c5"))
        }
        val finished = System.currentTimeMillis()
        println("HTTP sync: ${finished-init} millis")
    }

    @GetMapping("/http/async")
    fun httpAsyncBenchmark() {
        val init = System.currentTimeMillis()
        IntStream.range(1, 5000).parallel().forEach {
            httpClient.getCompanyTaxes(UUID.fromString("48ee19f8-d1fb-47b2-a028-ab9ace0ca8c5"))
        }
        val finished = System.currentTimeMillis()
        println("HTTP async: ${finished-init} millis")
    }
}
