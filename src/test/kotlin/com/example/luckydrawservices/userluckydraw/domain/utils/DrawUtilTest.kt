package com.example.luckydrawservices.userluckydraw.domain.utils

import com.example.luckydrawservices.fixture.PrizeFixture.prizeItem
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

        val prizeItemList = listOf(
            prizeItem,
            prizeItem.copy(id = BigInteger.TWO, stock = BigInteger.ZERO),
            prizeItem.copy(id = prizeIdthree, stock = BigInteger.ZERO),
            prizeItem.copy(id = prizeIdFour, stock = BigInteger.ZERO),
            prizeItem.copy(id = prizeIdFive, stock = BigInteger.ZERO),
        )

        val prizedrew = drawUtil.getPrizeByRandom(prizeItemList)

        Assertions.assertEquals(prizeItem, prizedrew)
    }

    @Test
    fun `should get no prize given all prizes out of stock`() {

        val prizeIdthree = BigInteger.valueOf(3)
        val prizeIdFour = BigInteger.valueOf(4)
        val prizeIdFive = BigInteger.valueOf(5)

        val prizeItemList = listOf(
            prizeItem.copy(stock = BigInteger.ZERO),
            prizeItem.copy(id = BigInteger.TWO, stock = BigInteger.ZERO),
            prizeItem.copy(id = prizeIdthree, stock = BigInteger.ZERO),
            prizeItem.copy(id = prizeIdFour, stock = BigInteger.ZERO),
            prizeItem.copy(id = prizeIdFive, stock = BigInteger.ZERO),
        )

        val prizedrew = drawUtil.getPrizeByRandom(prizeItemList)

        Assertions.assertNull(prizedrew)
    }
}