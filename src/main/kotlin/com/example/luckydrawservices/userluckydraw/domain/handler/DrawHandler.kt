package com.example.luckydrawservices.userluckydraw.domain.handler

import com.example.luckydrawservices.prize.query.model.PrizeInfo
import org.springframework.stereotype.Component

@Component
class DrawHandler {
    fun getPrizeByRandom(prizes: List<PrizeInfo>): PrizeInfo?{
        val total = prizes.sumOf { it.stock.toInt() }
        val random = (1..total).random()
        var sum = 0
        for (prize in prizes) {
            sum += prize.stock.toInt()
            if (random <= sum) {
                return prize
            }
        }
        return null
    }
}