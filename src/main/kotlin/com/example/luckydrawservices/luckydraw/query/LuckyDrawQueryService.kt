package com.example.luckydrawservices.luckydraw.query

import com.example.luckydrawservices.luckydraw.query.mapper.LuckyDrawMapper.Companion.MAPPER
import com.example.luckydrawservices.luckydraw.query.repository.LuckyDrawRepository
import com.example.luckydrawservices.luckydraw.query.response.LuckyDrawInfoResponse
import com.example.luckydrawservices.prize.query.repository.PrizeRepository
import org.springframework.stereotype.Service

@Service
class LuckyDrawQueryService(
    private val repository: LuckyDrawRepository,
    private val prizeRepository: PrizeRepository
) {
    fun retrieveLuckyDraws(): List<LuckyDrawInfoResponse>{
        val luckyDraws = repository.retrieveLuckyDraws()

        return luckyDraws.map {
            val prizeNameList = prizeRepository
                .findPrizesByLuckyDrawId(it.id).map{
                    prizeInfo -> prizeInfo.name
                }
            MAPPER.toLuckyDrawInfoResponse(it,prizeNameList)
        }
    }
}