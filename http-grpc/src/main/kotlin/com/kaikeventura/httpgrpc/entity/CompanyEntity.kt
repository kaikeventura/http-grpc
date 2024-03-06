package com.kaikeventura.httpgrpc.entity

import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.OneToMany
import org.hibernate.annotations.JdbcType
import org.hibernate.type.descriptor.jdbc.VarcharJdbcType
import java.util.UUID

@Entity(name = "company")
data class CompanyEntity(
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @JdbcType(VarcharJdbcType::class)
    val id: UUID,

    val name: String,

    @OneToMany(mappedBy = "company")
    val taxes: Set<TaxEntity>
)
