package com.example.luckydrawservices.luckydraw.query.repository

import com.example.luckydrawservices.luckydraw.query.model.LuckyDrawInfo
import java.math.BigInteger
import org.springframework.stereotype.Repository

@Repository
class LuckyDrawRepositoryImp(
    val jpaRepository: LuckyDrawJpaRepository
): LuckyDrawRepository {
    override fun retrieveLuckyDraws(): List<LuckyDrawInfo> {
        val results = jpaRepository.findAll()
        return results.map {
            LuckyDrawInfo(it.id, it.name,it.description, it.maxEntries ,it.entryNumber)
        }
    }

    override fun retrieveLuckyDrawById(luckDrawId: BigInteger): LuckyDrawInfo {
        val result = jpaRepository.findById(luckDrawId).get()
        return  LuckyDrawInfo(result.id, result.name,result.description, result.maxEntries ,result.entryNumber)
    }
}