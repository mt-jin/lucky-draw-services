package com.example.luckydrawservices.prize.query.model

import java.math.BigInteger

data class PrizeInfo(
    val id: BigInteger,
    val luckDrawId: BigInteger,
    val name: String,
    var stock: BigInteger
) {
    fun isOutOfStock(): Boolean {
        return stock <= BigInteger.ZERO
    }

    fun deductStock() {
        stock = stock.minus(BigInteger.ONE)
    }
}
