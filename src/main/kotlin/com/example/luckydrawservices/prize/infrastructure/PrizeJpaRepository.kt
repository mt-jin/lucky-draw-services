package com.example.luckydrawservices.prize.infrastructure

import com.example.luckydrawservices.prize.infrastructure.PrizeEntity
import java.math.BigInteger
import org.springframework.data.jpa.repository.JpaRepository

interface PrizeJpaRepository: JpaRepository<PrizeEntity, BigInteger> {

    fun findAllByLuckyDrawId(luckyDrawId: BigInteger):List<PrizeEntity>
}
