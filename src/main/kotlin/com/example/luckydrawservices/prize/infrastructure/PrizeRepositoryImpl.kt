package com.example.luckydrawservices.prize.infrastructure

import com.example.luckydrawservices.prize.domain.model.PrizeItem
import com.example.luckydrawservices.prize.domain.repository.PrizeRepository
import com.example.luckydrawservices.prize.query.model.PrizeInfo
import java.math.BigInteger
import org.springframework.stereotype.Repository

@Repository
class PrizeRepositoryImpl(
    val jpaRepository: PrizeJpaRepository
) : PrizeRepository {
    override fun findPrizesByLuckyDrawId(luckyDrawId: BigInteger): List<PrizeInfo> {
        val results = jpaRepository.findAllByLuckyDrawId(luckyDrawId)
        return results.map {
            PrizeInfo(it.id,it.luckyDrawId,it.name, it.stock)
        }
    }

    override fun retrievePrizeById(prizeId: BigInteger): PrizeInfo {
        val prize = jpaRepository.findById(prizeId).get()
        return PrizeInfo(prize.id, prize.luckyDrawId, prize.name, prize.stock)
    }

    override fun updatePrizeStock(prizeItem: PrizeItem) {
        val prize = jpaRepository.findById(prizeItem.id).get().copy(stock = prizeItem.stock)
        jpaRepository.save(prize)
    }

}