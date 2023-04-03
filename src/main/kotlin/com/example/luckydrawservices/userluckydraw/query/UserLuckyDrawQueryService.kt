package com.example.luckydrawservices.userluckydraw.query

import com.example.luckydrawservices.luckydraw.query.repository.LuckyDrawRepository
import com.example.luckydrawservices.prize.query.repository.PrizeRepository
import com.example.luckydrawservices.userluckydraw.query.repository.UserLuckyDrawRepository
import com.example.luckydrawservices.userluckydraw.query.response.UserLuckyDrawInfoResponse
import java.math.BigInteger
import org.springframework.stereotype.Service

@Service
class UserLuckyDrawQueryService(
    private val luckyDrawRepository: LuckyDrawRepository,
    private val prizeRepository: PrizeRepository,
    private val userLuckyDrawRepository: UserLuckyDrawRepository
) {
    fun retrieveUserLuckyDrawsByUserId(userId: BigInteger): List<UserLuckyDrawInfoResponse> {
        val userLuckyDraws = userLuckyDrawRepository.retrieveUserLuckyDrawsByUserId(userId)
        return userLuckyDraws.map {userLuckyDraw ->
            val prizeName = prizeRepository.retrievePrizeById(userLuckyDraw.prizeId).name
            val rewardName = luckyDrawRepository.retrieveLuckyDrawById(userLuckyDraw.luckyDrawId).name
            UserLuckyDrawInfoResponse(userLuckyDraw.id, rewardName, prizeName)
        }
    }
}
