package com.example.luckydrawservices.luckydraw.query.response

import java.math.BigInteger

class LuckyDrawInfoResponse(
    val id: BigInteger,
    val name: String?,
    val description: String?,
    val totalEntryLimit: BigInteger,
    val totalEntryNumber: BigInteger?,
    val prizeNames: List<String>
) {
}