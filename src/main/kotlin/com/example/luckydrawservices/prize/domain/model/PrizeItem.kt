package com.example.luckydrawservices.prize.domain.model

import java.math.BigInteger

data class PrizeItem(
    val id: BigInteger,
    val name: String,
//    val description: String?,
//    val prizeValue: String?,
    var stock: BigInteger,
//    val probability: Double?
){
    fun deductStock() {
        stock = stock.minus(BigInteger.ONE)
    }
}