package com.kaikeventura.httpgrpc.entity

import jakarta.persistence.*
import org.hibernate.annotations.JdbcType
import org.hibernate.type.descriptor.jdbc.VarcharJdbcType
import java.util.UUID

@Entity(name = "tax")
data class TaxEntity(
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @JdbcType(VarcharJdbcType::class)
    val id: UUID,

    @Enumerated(EnumType.STRING)
    val type: TaxType,

    val percentage: Double,

    @ManyToOne
    @JoinColumn(name = "company_id", referencedColumnName = "id")
    val company: CompanyEntity
)

enum class TaxType {
    IPI, IOF, PIS
}
