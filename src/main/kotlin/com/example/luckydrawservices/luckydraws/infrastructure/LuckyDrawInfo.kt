package com.example.luckydrawservices.luckydraws.infrastructure

import java.math.BigInteger

data class LuckyDrawInfo(
    val id: BigInteger,
    val name: String?,
    val description: String?,
    val maxEntries: BigInteger,
    val entryNumber: BigInteger?
)
