package com.example.luckydrawservices.userluckydraw.domain

import com.example.luckydrawservices.prize.query.repository.PrizeRepository
import com.example.luckydrawservices.userluckydraw.domain.handler.DrawHandler
import com.example.luckydrawservices.userluckydraw.domain.response.DrawLuckyDrawResponse
import com.example.luckydrawservices.userluckydraw.query.model.UserLuckyDraw
import com.example.luckydrawservices.userluckydraw.query.repository.UserLuckyDrawRepository
import java.math.BigInteger
import org.springframework.stereotype.Service

@Service
class UserLuckyDrawApplicationService(
    val prizeRepository: PrizeRepository,
    val userLuckyDrawRepository: UserLuckyDrawRepository,
    val drawHandler: DrawHandler
) {
    fun drawLuckyDraw(luckyDrawId: BigInteger, userId: BigInteger): DrawLuckyDrawResponse? {
//        查库存
//        查奖品
        val prizes = prizeRepository.findPrizesByLuckyDrawId(luckyDrawId)
//        随机抽奖
        val prizeDrew = drawHandler.getPrizeByRandom(prizes)
//        改变库存
        prizeDrew?.let {
            val prize = prizeRepository.retrievePrizeById(it.id)
            val stock = prize.stock.minus(BigInteger.ONE)
            prizeRepository.updatePrizeStock(prize.copy(stock = stock))
            //        写结果到userLuckyDraw table
            val userLuckyDraw = UserLuckyDraw(luckyDrawId = luckyDrawId, userId = userId, prizeId = it.id)
            userLuckyDrawRepository.saveUserLuckyDraw(userLuckyDraw)
            return DrawLuckyDrawResponse(luckyDrawId, prize.name)
        }

        return null
    }

}
