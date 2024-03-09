package com.kaikeventura.httpgrpc.bench.http

import org.springframework.stereotype.Component
import org.springframework.web.client.RestTemplate
import org.springframework.web.client.getForEntity
import org.springframework.web.client.getForObject
import java.net.URI
import java.util.*

@Component
class HttpClient {

    private val restTemplate = RestTemplate()
    fun getCompanyTaxes(companyId: UUID) {
        restTemplate.getForEntity("http://localhost:8080/company/$companyId/taxes", String::class.java)
    }
}
