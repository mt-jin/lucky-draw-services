package com.example.luckydrawservices.luckydraw.query.repository

import com.example.luckydrawservices.luckydraw.infrastructure.LuckyDrawEntity
import com.example.luckydrawservices.luckydraw.infrastructure.LuckyDrawStatus
import com.example.luckydrawservices.luckydraw.query.model.LuckyDrawInfo
import java.math.BigInteger

interface LuckyDrawRepository {
    fun retrieveActiveLuckyDraws(): List<LuckyDrawInfo>
    fun retrieveLuckyDrawById(luckDrawId: BigInteger): LuckyDrawInfo
    fun updateLuckyDrawStatus(
        luckyDrawId: BigInteger,
        luckyDrawStatus: LuckyDrawStatus
    ): LuckyDrawEntity

    fun updateLuckyDrawEntry(luckyDrawInfo: LuckyDrawInfo):LuckyDrawEntity
}