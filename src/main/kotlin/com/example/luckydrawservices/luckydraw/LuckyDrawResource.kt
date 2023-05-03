package com.example.luckydrawservices.luckydraw

import com.example.luckydrawservices.luckydraw.query.LuckyDrawQueryService
import java.math.BigInteger
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RestController

@RestController
class LuckyDrawResource(
    val luckyDrawQueryService: LuckyDrawQueryService
) {

    @GetMapping("/luckydraws")
    fun retrieveActiveLuckyDraws() = luckyDrawQueryService.retrieveActiveLuckyDraws()

    @GetMapping("/luckydraws/{luckyDrawId}")
    fun retrieveLuckyDrawById(
        @PathVariable("luckyDrawId") luckydrawId: BigInteger
    ) = luckyDrawQueryService.retrieveLuckyDrawById(luckydrawId)
}