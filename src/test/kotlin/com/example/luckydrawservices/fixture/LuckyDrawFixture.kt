package com.example.luckydrawservices.fixture

import com.example.luckydrawservices.luckydraw.domain.model.LuckyDrawWithItems
import com.example.luckydrawservices.luckydraw.infrastructure.LuckyDrawMode
import com.example.luckydrawservices.luckydraw.infrastructure.LuckyDrawStatus
import com.example.luckydrawservices.prize.domain.model.PrizeItem
import java.math.BigInteger
import java.time.LocalDateTime
import java.time.Period

object LuckyDrawFixture {

    val item = PrizeItem(
        BigInteger.ONE,
        "test prize",
        BigInteger.TEN)


    val luckyDrawWithItems =
        LuckyDrawWithItems(
            BigInteger.ONE,
            "test lucky draw",
            "test lucky draw",
            BigInteger.TEN,
            BigInteger.ONE,
            LuckyDrawMode.BYSTOCK,
            LuckyDrawStatus.ACTIVE,
            0,
            LocalDateTime.now().minus(Period.ofDays(1)),
            LocalDateTime.now().plus(Period.ofDays(1)),
            BigInteger.TWO,
            listOf(item)
        )
}