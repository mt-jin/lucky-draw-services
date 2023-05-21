package com.example.luckydrawservices.luckydraw.query

import com.example.luckydrawservices.luckydraw.domain.repository.LuckyDrawRepository
import com.example.luckydrawservices.luckydraw.query.mapper.LuckyDrawQueryMapper.Companion.MAPPER
import com.example.luckydrawservices.luckydraw.query.response.LuckyDrawInfoResponse
import com.example.luckydrawservices.prize.domain.repository.PrizeRepository
import java.math.BigInteger
import org.springframework.stereotype.Service

@Service
class LuckyDrawQueryService(
    private val repository: LuckyDrawRepository,
    private val prizeRepository: PrizeRepository
) {
    fun retrieveActiveLuckyDraws(): List<LuckyDrawInfoResponse>{
        val luckyDraws = repository.retrieveActiveLuckyDraws()

        return luckyDraws.map {
            val prizeNameList = prizeRepository
                .findPrizesByLuckyDrawId(it.id).map{
                    prizeInfo -> prizeInfo.name
                }
            MAPPER.toLuckyDrawInfoResponse(it,prizeNameList)
        }
    }

    fun retrieveLuckyDrawById(luckyDrawId: BigInteger) = repository.retrieveLuckyDrawById(luckyDrawId)
}