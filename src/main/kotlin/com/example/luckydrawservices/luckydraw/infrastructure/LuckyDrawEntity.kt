package com.example.luckydrawservices.luckydraw.infrastructure

import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.EnumType.STRING
import jakarta.persistence.Enumerated
import jakarta.persistence.Id
import jakarta.persistence.Table
import java.math.BigInteger
import java.time.LocalDateTime

@Entity
@Table(name = "lucky_draw")
data class LuckyDrawEntity (
    @Id
    val id: BigInteger,
    val name: String?,
    val description: String?,
    val maxEntries: BigInteger,
    var entryNumber: BigInteger? = BigInteger.ZERO,
    @Enumerated(STRING)
    val mode: LuckyDrawMode? = LuckyDrawMode.BYSTOCK,
    val categories: String? = "",
    val tags: String? = "",
    @Enumerated(STRING)
    var status: LuckyDrawStatus? = LuckyDrawStatus.ACTIVE,
    val deleted: Int? = 0,
    @Column(columnDefinition = "TIMESTAMP")
    val startTime: LocalDateTime?,
    @Column(columnDefinition = "TIMESTAMP")
    val endTime: LocalDateTime?,
)
