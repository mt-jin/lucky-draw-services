package com.example.luckydrawservices.luckydraw.query.model

import com.example.luckydrawservices.luckydraw.infrastructure.LuckyDrawStatus
import java.math.BigInteger

data class LuckyDrawInfo(
    val id: BigInteger,
    val name: String?,
    val description: String?,
    val totalEntryLimit: BigInteger,
    var totalEntryNumber: BigInteger? = BigInteger.ZERO,
    val status: LuckyDrawStatus?
)