package com.kaikeventura.httpgrpc

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class HttpGrpcApplication

fun main(args: Array<String>) {
	runApplication<HttpGrpcApplication>(*args)
}
