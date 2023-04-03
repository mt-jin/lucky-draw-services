package com.example.luckydrawservices.userluckydraw.query.response

import java.math.BigInteger

data class UserLuckyDrawInfoResponse (
    val id: BigInteger,
    val luckyDrawName: String?,
    val prizeName: String?
){

}
