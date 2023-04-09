package com.example.luckydrawservices.prize.query.model

import java.math.BigInteger

data class PrizeInfo(
    val id: BigInteger,
    val luckDrawId: BigInteger,
    val name: String,
    val stock: BigInteger
) {

}
