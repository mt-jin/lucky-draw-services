package com.example.luckydrawservices.luckydraw.infrastructure

import com.example.luckydrawservices.common.exception.notfound.LuckyDrawNotFoundException
import com.example.luckydrawservices.luckydraw.domain.model.LuckyDrawWithItems
import com.example.luckydrawservices.luckydraw.domain.repository.LuckyDrawRepository
import com.example.luckydrawservices.luckydraw.mapper.LuckyDrawMapper
import com.example.luckydrawservices.luckydraw.query.model.LuckyDrawInfo
import com.example.luckydrawservices.prize.domain.repository.PrizeRepository
import java.math.BigInteger
import org.springframework.stereotype.Repository

@Repository
class LuckyDrawRepositoryImp(
    val jpaRepository: LuckyDrawJpaRepository,
    val prizeRepository: PrizeRepository
) : LuckyDrawRepository {
    override fun retrieveActiveLuckyDraws(): List<LuckyDrawInfo> {
        val results = jpaRepository.findAllByStatus()
        return results.map {
            LuckyDrawInfo(it.id, it.name, it.description, it.totalEntryLimit, it.totalEntryNumber!!, it.status)
        }
    }

    override fun retrieveLuckyDrawWithItems(luckyDrawId: BigInteger): LuckyDrawWithItems {
        val luckyDrawEntity = jpaRepository.findById(luckyDrawId)
            .orElseThrow { LuckyDrawNotFoundException("Lucky draw with id $luckyDrawId not found in database") }
        val prizes = prizeRepository.findPrizesByLuckyDrawId(luckyDrawId)
        return LuckyDrawMapper.MAPPER.toLuckyDrawWithItems(luckyDrawEntity, prizes)
    }

    override fun retrieveLuckyDrawById(luckyDrawId: BigInteger): LuckyDrawInfo {
        val result = jpaRepository.findById(luckyDrawId).orElseThrow {
            LuckyDrawNotFoundException("Lucky draw with id $luckyDrawId not found in database")
        }
        return LuckyDrawInfo(
            result.id,
            result.name,
            result.description,
            result.totalEntryLimit,
            result.totalEntryNumber!!,
            result.status
        )
    }

    override fun updateLuckyDrawWithItems(luckyDrawWithItems: LuckyDrawWithItems) {
        val luckyDrawEntity = LuckyDrawMapper.MAPPER.mapToLuckyDrawEntity(luckyDrawWithItems)
        updateLuckyDrawEntity(luckyDrawEntity)

        luckyDrawWithItems.items.forEach { prizeRepository.updatePrizeStock(it) }
    }

    private fun updateLuckyDrawEntity(luckyDrawEntity: LuckyDrawEntity) {
        jpaRepository.save(luckyDrawEntity)
    }
}