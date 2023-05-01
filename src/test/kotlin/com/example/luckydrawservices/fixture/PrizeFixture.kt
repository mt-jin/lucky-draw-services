package com.example.luckydrawservices.fixture

import com.example.luckydrawservices.prize.query.model.PrizeInfo
import java.math.BigInteger

object PrizeFixture {
    val prizeInfo = PrizeInfo(BigInteger.ONE, BigInteger.ONE, "test prize", BigInteger.TEN)

}