package com.example.luckydrawservices.userluckydraw.query.repository

import com.example.luckydrawservices.userluckydraw.query.model.UserLuckyDraw
import java.math.BigInteger

interface UserLuckyDrawRepository{
    fun retrieveUserLuckyDrawsByUserId(userId: BigInteger):List<UserLuckyDraw>
    fun saveUserLuckyDraw(userLuckyDraw: UserLuckyDraw):UserLuckyDraw
    fun retrieveUserLuckyDrawsByUserIdAndLuckyDrawId(userId: BigInteger, luckyDrawId: BigInteger):List<UserLuckyDraw>?
}
