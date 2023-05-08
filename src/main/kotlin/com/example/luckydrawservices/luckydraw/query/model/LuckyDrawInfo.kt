package com.example.luckydrawservices.luckydraw.query.model

import com.example.luckydrawservices.luckydraw.infrastructure.LuckyDrawStatus
import java.math.BigInteger

data class LuckyDrawInfo(
    val id: BigInteger,
    val name: String?,
    val description: String?,
    val maxEntries: BigInteger,
    var entryNumber: BigInteger? = BigInteger.ZERO,
    val status: LuckyDrawStatus?
){
    fun isActive(): Boolean {
        return status == LuckyDrawStatus.ACTIVE
    }

    fun addEntry() {
        entryNumber = entryNumber?.plus(BigInteger.ONE)
    }

    fun isEntryFull(): Boolean{
        return maxEntries <= entryNumber
    }
}