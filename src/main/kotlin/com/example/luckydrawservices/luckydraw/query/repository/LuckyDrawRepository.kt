package com.example.luckydrawservices.luckydraw.query.repository

import com.example.luckydrawservices.luckydraw.query.model.LuckyDrawInfo

interface LuckyDrawRepository {
    fun retrieveLuckyDraws():List<LuckyDrawInfo>
}