package com.example.luckydrawservices.luckydraw.domain.repository

import com.example.luckydrawservices.luckydraw.domain.model.LuckyDrawWithItems
import com.example.luckydrawservices.luckydraw.query.model.LuckyDrawInfo
import java.math.BigInteger

interface LuckyDrawRepository {
    fun retrieveActiveLuckyDraws(): List<LuckyDrawInfo>
    fun retrieveLuckyDrawById(luckyDrawId: BigInteger): LuckyDrawInfo
    fun retrieveLuckyDrawWithItems(luckyDrawId: BigInteger) :LuckyDrawWithItems
    fun updateLuckyDrawWithItems(luckyDrawWithItems: LuckyDrawWithItems)
}