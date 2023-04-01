package com.example.luckydrawservices.luckydraws.infrastructure

interface LuckyDrawRepository {
    fun retrieveLuckyDraws():List<LuckyDrawInfo>
}