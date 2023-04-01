package com.example.luckydrawservices.prize.infrastructure

import jakarta.persistence.Entity
import jakarta.persistence.Id
import jakarta.persistence.Table
import java.math.BigInteger

@Entity
@Table(name = "prize")
data class PrizeEntity(
    @Id
    val id: BigInteger,
    val luckyDrawId: BigInteger,
    val name: String,
    val description: String?,
    val value: String?,
    val stock: BigInteger,
    val probability: Double?,
    val categories: String?
) {

}
