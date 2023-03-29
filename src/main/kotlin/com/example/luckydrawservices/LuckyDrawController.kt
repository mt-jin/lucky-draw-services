package com.example.luckydrawservices

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class LuckyDrawController {

    @GetMapping("/luckydraws")
    fun retrieveLuckyDraws(): String{
        return "Hello!"
    }
}