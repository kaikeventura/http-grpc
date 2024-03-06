package com.kaikeventura.httpgrpc.repository

import com.kaikeventura.httpgrpc.entity.CompanyEntity
import com.kaikeventura.httpgrpc.entity.TaxEntity
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import java.util.UUID

@Repository
interface CompanyRepository : JpaRepository<CompanyEntity, UUID>

@Repository
interface TaxRepository : JpaRepository<TaxEntity, UUID> {
    fun findAllByCompanyId(companyId: UUID): Set<TaxEntity>
}
