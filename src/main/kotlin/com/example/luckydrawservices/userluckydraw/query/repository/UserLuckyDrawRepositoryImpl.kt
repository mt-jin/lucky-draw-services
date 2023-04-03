package com.example.luckydrawservices.userluckydraw.query.repository

import com.example.luckydrawservices.userluckydraw.query.model.UserLuckyDraw
import java.math.BigInteger
import org.springframework.stereotype.Repository

@Repository
class UserLuckyDrawRepositoryImpl(
    val userLuckyDrawJpaRepository: UserLuckyDrawJpaRepository
) : UserLuckyDrawRepository {
    override fun retrieveUserLuckyDrawsByUserId(userId: BigInteger):List<UserLuckyDraw> {
        val results = userLuckyDrawJpaRepository.findALlByUserId(userId)
        results.forEach{ println("UserLuckyDraw"+it.id + it.luckyDrawId + it.userId + it.prizeId) }
        return results.map { UserLuckyDraw(id = it.id, luckyDrawId = it.luckyDrawId,userId =it.userId, prizeId = it.prizeId) }
    }
}