package com.example.luckydrawservices.userluckydraw.domain.Utils

import com.example.luckydrawservices.prize.query.model.PrizeInfo
import org.springframework.stereotype.Component

@Component
class DrawUtil {
    fun getPrizeByRandom(prizes: List<PrizeInfo>): PrizeInfo?{
        val total = prizes.sumOf { it.stock.toInt() }
        if (total == 0) {
            return null
        }
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