package com.example.luckydrawservices.userluckydraw.domain.utils

import com.example.luckydrawservices.prize.domain.model.PrizeItem
import org.springframework.stereotype.Component

@Component
class DrawUtil {
    fun getPrizeByRandom(prizes: List<PrizeItem>): PrizeItem?{

        val total = getTotalStock(prizes)
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

    fun getTotalStock(prizes: List<PrizeItem>): Int{
        return prizes.sumOf { it.stock.toInt() }
    }
}