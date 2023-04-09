package com.example.luckydrawservices.userluckydraw.domain.response

import java.math.BigInteger

data class DrawLuckyDrawResponse(
    val luckyDrawId: BigInteger,
    val prizeName: String
)

