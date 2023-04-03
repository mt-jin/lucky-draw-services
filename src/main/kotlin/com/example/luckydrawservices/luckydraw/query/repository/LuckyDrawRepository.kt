package com.example.luckydrawservices.luckydraw.query.repository

import com.example.luckydrawservices.luckydraw.query.model.LuckyDrawInfo
import java.math.BigInteger

interface LuckyDrawRepository {
    fun retrieveLuckyDraws():List<LuckyDrawInfo>
    fun retrieveLuckyDrawById(luckDrawId: BigInteger): LuckyDrawInfo
}