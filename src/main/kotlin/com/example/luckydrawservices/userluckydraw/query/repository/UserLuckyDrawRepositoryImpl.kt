package com.example.luckydrawservices.userluckydraw.query.repository

import com.example.luckydrawservices.userluckydraw.domain.mapper.UserLuckyDrawMapper.Companion.MAPPER
import com.example.luckydrawservices.userluckydraw.query.model.UserLuckyDraw
import java.math.BigInteger

import org.springframework.stereotype.Repository

@Repository
class UserLuckyDrawRepositoryImpl(
    val userLuckyDrawJpaRepository: UserLuckyDrawJpaRepository
) : UserLuckyDrawRepository {
    override fun retrieveUserLuckyDrawsByUserId(userId: BigInteger):List<UserLuckyDraw> {
        val results = userLuckyDrawJpaRepository.findALlByUserId(userId)
//        results.forEach{ println("UserLuckyDraw"+it.id + it.luckyDrawId + it.userId + it.prizeId) }
        return results.map { UserLuckyDraw(luckyDrawId = it.luckyDrawId,userId =it.userId, prizeId = it.prizeId) }
    }

    override fun saveUserLuckyDraw(userLuckyDraw: UserLuckyDraw) {
        val userLuckyDrawEntity = MAPPER.toUserLuckyDrawEntity(userLuckyDraw)
        userLuckyDrawEntity.completed = true
        userLuckyDrawJpaRepository.saveAndFlush(userLuckyDrawEntity)
    }
}