package com.example.luckydrawservices.luckydraw.infrastructure

import jakarta.persistence.Entity
import jakarta.persistence.Id
import jakarta.persistence.Table
import java.math.BigInteger

@Entity
@Table(name = "lucky_draw")
data class LuckyDrawEntity (
    @Id
    val id: BigInteger,
    val name: String?,
    val description: String?,
    val maxEntries: BigInteger,
    val entryNumber: BigInteger? = BigInteger.ZERO,
    val mode: String?,
    val categories: String?,
    val tags: String?,
    val status: Int?,
    val deleted: Int?,
){}
