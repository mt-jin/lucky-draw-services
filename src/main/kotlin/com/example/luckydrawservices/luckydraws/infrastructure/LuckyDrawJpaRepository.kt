package com.example.luckydrawservices.luckydraws.infrastructure

import java.math.BigInteger
import org.springframework.data.jpa.repository.JpaRepository

interface LuckyDrawJpaRepository: JpaRepository<LuckyDrawEntity, BigInteger> {

}