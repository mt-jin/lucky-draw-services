package com.example.luckydrawservices.userluckydraw.query.model

import java.math.BigInteger


data class UserLuckyDraw(

    val luckyDrawId: BigInteger,
    val userId: BigInteger,
    val prizeId: BigInteger
)
