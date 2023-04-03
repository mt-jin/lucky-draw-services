package com.example.luckydrawservices.userluckydraw

import com.example.luckydrawservices.userluckydraw.query.UserLuckyDrawQueryService
import java.math.BigInteger
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RestController

@RestController
class UserLuckyDrawResource (
    val userLuckyDrawQueryService: UserLuckyDrawQueryService
){

    @GetMapping("/userluckydraws/{userId}")
    fun retrieveUserLuckyDraws(
        @PathVariable userId: BigInteger
    ) = userLuckyDrawQueryService.retrieveUserLuckyDrawsByUserId(userId)
}