package com.example.luckydrawservices.luckydraws.query.response

import java.math.BigInteger

class LuckyDrawInfoResponse(
    val id: BigInteger,
    val name: String?,
    val description: String?,
    val maxEntries: BigInteger,
    val entryNumber: BigInteger?
) {
}