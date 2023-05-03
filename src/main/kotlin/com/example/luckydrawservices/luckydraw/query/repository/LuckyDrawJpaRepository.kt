package com.example.luckydrawservices.luckydraw.query.repository

import com.example.luckydrawservices.luckydraw.infrastructure.LuckyDrawEntity
import com.example.luckydrawservices.luckydraw.infrastructure.LuckyDrawStatus
import java.math.BigInteger
import org.springframework.data.jpa.repository.JpaRepository

interface LuckyDrawJpaRepository: JpaRepository<LuckyDrawEntity, BigInteger> {
    fun findAllByStatus(
        status: LuckyDrawStatus = LuckyDrawStatus.ACTIVE
    ): List<LuckyDrawEntity>
}