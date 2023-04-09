package com.example.luckydrawservices.prize.query.repository

import com.example.luckydrawservices.prize.query.model.PrizeInfo
import java.math.BigInteger

interface PrizeRepository {
    fun findPrizesByLuckyDrawId(luckyDrawId: BigInteger): List<PrizeInfo>
    fun retrievePrizeById(prizeId: BigInteger): PrizeInfo
    fun updatePrizeStock(prizeInfo: PrizeInfo): PrizeInfo
}
