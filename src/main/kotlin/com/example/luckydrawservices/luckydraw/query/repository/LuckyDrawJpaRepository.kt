package com.example.luckydrawservices.luckydraw.query.repository

import com.example.luckydrawservices.luckydraw.infrastructure.LuckyDrawEntity
import java.math.BigInteger
import org.springframework.data.jpa.repository.JpaRepository

interface LuckyDrawJpaRepository: JpaRepository<LuckyDrawEntity, BigInteger> {

}