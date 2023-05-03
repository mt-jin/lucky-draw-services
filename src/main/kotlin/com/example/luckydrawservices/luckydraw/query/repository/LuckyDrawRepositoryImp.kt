package com.example.luckydrawservices.luckydraw.query.repository

import com.example.luckydrawservices.common.exception.LuckyDrawNotFoundException
import com.example.luckydrawservices.luckydraw.query.model.LuckyDrawInfo
import java.math.BigInteger
import org.springframework.stereotype.Repository

@Repository
class LuckyDrawRepositoryImp(
    val jpaRepository: LuckyDrawJpaRepository
): LuckyDrawRepository {
    override fun retrieveActiveLuckyDraws(): List<LuckyDrawInfo> {
        val results = jpaRepository.findAllByStatus()
        return results.map {
            LuckyDrawInfo(it.id, it.name,it.description, it.maxEntries ,it.entryNumber)
        }
    }

    override fun retrieveLuckyDrawById(luckDrawId: BigInteger): LuckyDrawInfo {
        val result = jpaRepository.findById(luckDrawId).orElseThrow{
            LuckyDrawNotFoundException("Lucky draw with id $luckDrawId not found in database")
        }
        return  LuckyDrawInfo(result.id, result.name,result.description, result.maxEntries ,result.entryNumber)
    }
}