package com.example.luckydrawservices.luckydraw.query.response

import java.math.BigInteger

class LuckyDrawInfoResponse(
    val id: BigInteger,
    val name: String?,
    val description: String?,
    val maxEntries: BigInteger,
    val entryNumber: BigInteger?,
    val prizeNames: List<String>
) {
}