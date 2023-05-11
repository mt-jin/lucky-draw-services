package com.example.luckydrawservices.luckydraw.query.repository

import com.example.luckydrawservices.common.exception.notfound.LuckyDrawNotFoundException
import com.example.luckydrawservices.luckydraw.infrastructure.LuckyDrawEntity
import com.example.luckydrawservices.luckydraw.infrastructure.LuckyDrawStatus
import com.example.luckydrawservices.luckydraw.query.model.LuckyDrawInfo
import java.math.BigInteger
import org.springframework.stereotype.Repository

@Repository
class LuckyDrawRepositoryImp(
    val jpaRepository: LuckyDrawJpaRepository
) : LuckyDrawRepository {
    override fun retrieveActiveLuckyDraws(): List<LuckyDrawInfo> {
        val results = jpaRepository.findAllByStatus()
        return results.map {
            LuckyDrawInfo(it.id, it.name, it.description, it.maxEntries, it.entryNumber!!, it.status)
        }
    }

    override fun retrieveLuckyDrawById(luckyDrawId: BigInteger): LuckyDrawInfo {
        val result = jpaRepository.findById(luckyDrawId).orElseThrow {
            LuckyDrawNotFoundException("Lucky draw with id $luckyDrawId not found in database")
        }
        return LuckyDrawInfo(
            result.id,
            result.name,
            result.description,
            result.maxEntries,
            result.entryNumber!!,
            result.status
        )
    }

    override fun updateLuckyDrawStatus(
        luckyDrawId: BigInteger,
        luckyDrawStatus: LuckyDrawStatus
    ): LuckyDrawEntity {
        val luckyDraw = jpaRepository.findById(luckyDrawId).orElseThrow {
            LuckyDrawNotFoundException("Lucky draw with id $luckyDrawId not found in database")
        }
        luckyDraw.updateStatus(luckyDrawStatus)
        jpaRepository.save(luckyDraw)
        return luckyDraw
    }

    override fun updateLuckyDrawEntry(luckyDrawInfo: LuckyDrawInfo): LuckyDrawEntity {
        val luckyDraw = jpaRepository.findById(luckyDrawInfo.id).orElseThrow {
            LuckyDrawNotFoundException("Lucky draw with id ${luckyDrawInfo.id} not found in database")
        }
        luckyDraw.addEntry()
        return jpaRepository.save(luckyDraw)
    }
}