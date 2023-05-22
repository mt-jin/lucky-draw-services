package com.example.luckydrawservices.userluckydraw.query.repository

import com.example.luckydrawservices.userluckydraw.infrastructure.UserLuckyDrawEntity
import java.math.BigInteger
import org.springframework.data.jpa.repository.JpaRepository

interface UserLuckyDrawJpaRepository:JpaRepository<UserLuckyDrawEntity,BigInteger> {

    fun findALlByUserId(userId: BigInteger):List<UserLuckyDrawEntity>
    fun findAllByUserIdAndLuckyDrawId(userId: BigInteger, luckyDrawId: BigInteger):List<UserLuckyDrawEntity>

}
