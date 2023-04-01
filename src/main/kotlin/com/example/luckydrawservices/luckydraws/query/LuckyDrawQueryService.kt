package com.example.luckydrawservices.luckydraws.query

import com.example.luckydrawservices.luckydraws.infrastructure.LuckyDrawMapper.Companion.MAPPER
import com.example.luckydrawservices.luckydraws.infrastructure.LuckyDrawRepository
import com.example.luckydrawservices.luckydraws.query.response.LuckyDrawInfoResponse
import org.springframework.stereotype.Service

@Service
class LuckyDrawQueryService(
    private val repository: LuckyDrawRepository
) {
    fun retrieveLuckyDraws(): List<LuckyDrawInfoResponse>{
        val luckyDraws = repository.retrieveLuckyDraws()
        return luckyDraws.map {MAPPER.toLuckyDrawInfoResponse(it)
        }
    }
}