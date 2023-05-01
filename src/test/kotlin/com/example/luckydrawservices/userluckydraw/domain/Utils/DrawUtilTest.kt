package com.example.luckydrawservices.userluckydraw.domain.Utils

import com.example.luckydrawservices.fixture.PrizeFixture.prizeInfo
import java.math.BigInteger
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test

class DrawUtilTest {

    val drawUtil = DrawUtil()

    @Test
    fun `should only get prize of positive stock`() {

        val prizeIdthree = BigInteger.valueOf(3)
        val prizeIdFour = BigInteger.valueOf(4)
        val prizeIdFive = BigInteger.valueOf(5)

        val prizeInfoList = listOf(
            prizeInfo,
            prizeInfo.copy(id = BigInteger.TWO, stock = BigInteger.ZERO),
            prizeInfo.copy(id = prizeIdthree, stock = BigInteger.ZERO),
            prizeInfo.copy(id = prizeIdFour, stock = BigInteger.ZERO),
            prizeInfo.copy(id = prizeIdFive, stock = BigInteger.ZERO),
        )

        val prizedrew = drawUtil.getPrizeByRandom(prizeInfoList)

        Assertions.assertEquals(prizeInfo, prizedrew)
    }

    @Test
    fun `should get no prize given all prizes out of stock`() {

        val prizeIdthree = BigInteger.valueOf(3)
        val prizeIdFour = BigInteger.valueOf(4)
        val prizeIdFive = BigInteger.valueOf(5)

        val prizeInfoList = listOf(
            prizeInfo.copy(stock = BigInteger.ZERO),
            prizeInfo.copy(id = BigInteger.TWO, stock = BigInteger.ZERO),
            prizeInfo.copy(id = prizeIdthree, stock = BigInteger.ZERO),
            prizeInfo.copy(id = prizeIdFour, stock = BigInteger.ZERO),
            prizeInfo.copy(id = prizeIdFive, stock = BigInteger.ZERO),
        )

        val prizedrew = drawUtil.getPrizeByRandom(prizeInfoList)

        Assertions.assertNull(prizedrew)
    }
}