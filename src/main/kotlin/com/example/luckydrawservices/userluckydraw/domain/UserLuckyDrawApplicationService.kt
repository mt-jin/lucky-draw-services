package com.example.luckydrawservices.userluckydraw.domain

import com.example.luckydrawservices.luckydraw.query.repository.LuckyDrawRepository
import com.example.luckydrawservices.prize.query.repository.PrizeRepository
import com.example.luckydrawservices.userluckydraw.domain.Utils.DrawUtil
import com.example.luckydrawservices.userluckydraw.domain.response.DrawLuckyDrawResponse
import com.example.luckydrawservices.userluckydraw.query.model.UserLuckyDraw
import com.example.luckydrawservices.userluckydraw.query.repository.UserLuckyDrawRepository
import java.math.BigInteger
import org.springframework.stereotype.Service

@Service
class UserLuckyDrawApplicationService(
    val prizeRepository: PrizeRepository,
    val userLuckyDrawRepository: UserLuckyDrawRepository,
    val luckyDrawRepository: LuckyDrawRepository,
    val drawUtil: DrawUtil
) {
    fun drawLuckyDraw(luckyDrawId: BigInteger, userId: BigInteger): DrawLuckyDrawResponse? {


        if (!checkEntry(luckyDrawId)){
            return null
        }
//      retrieve prize inventory
        val prizes = prizeRepository.findPrizesByLuckyDrawId(luckyDrawId)
//        draw
        val prizeDrew = drawUtil.getPrizeByRandom(prizes)
//        change inventory & save userLuckyDraw
        prizeDrew?.let {
            //?
            val prize = prizeRepository.retrievePrizeById(it.id)

            val stock = prize.stock.minus(BigInteger.ONE)
            prizeRepository.updatePrizeStock(prize.copy(stock = stock))
//            为什么不能改变val var, 为什么用copy
//            minus stock，prize的方法
            //        写结果到userLuckyDraw table
            val userLuckyDraw = UserLuckyDraw(luckyDrawId = luckyDrawId, userId = userId, prizeId = it.id)
            userLuckyDrawRepository.saveUserLuckyDraw(userLuckyDraw)
            return DrawLuckyDrawResponse(luckyDrawId, prize.name)
        }
//        !!代替?.let
        return null

    }

    private fun checkEntry(luckyDrawId: BigInteger): Boolean {
        val luckyDrawInfo = luckyDrawRepository.retrieveLuckyDrawById(luckyDrawId)
        luckyDrawInfo.let { return luckyDrawInfo.entryNumber!! < luckyDrawInfo.maxEntries }
    }

}
