package com.example.luckydrawservices.luckydraw

import com.example.luckydrawservices.luckydraw.query.LuckyDrawQueryService
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class LuckyDrawResource (
    val luckyDrawQueryService: LuckyDrawQueryService
){

    @GetMapping("/luckydraws")
    fun retrieveLuckyDraws() = luckyDrawQueryService.retrieveLuckyDraws()
}