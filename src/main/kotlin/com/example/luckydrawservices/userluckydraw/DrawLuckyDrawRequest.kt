package com.example.luckydrawservices.userluckydraw

import java.math.BigInteger

data class DrawLuckyDrawRequest(
    val luckyDrawId: BigInteger,
    val userId: BigInteger,
)
