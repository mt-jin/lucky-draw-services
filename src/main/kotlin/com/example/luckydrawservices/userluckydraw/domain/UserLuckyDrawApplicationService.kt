package com.example.luckydrawservices.userluckydraw.domain

import com.example.luckydrawservices.common.exception.LuckyDrawStatusException
import com.example.luckydrawservices.luckydraw.infrastructure.LuckyDrawStatus.ENDED
import com.example.luckydrawservices.luckydraw.infrastructure.LuckyDrawStatus.FULL
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

        val luckyDrawInfo = luckyDrawRepository.retrieveLuckyDrawById(luckyDrawId)
        if ( ! luckyDrawInfo.isActive()){
           throw LuckyDrawStatusException("Lucky Draw of id $luckyDrawId is not active")
        }

//      retrieve prize inventory
        val prizes = prizeRepository.findPrizesByLuckyDrawId(luckyDrawId)
//        draw
        val prizeDrew = drawUtil.getPrizeByRandom(prizes)
//        change inventory & save userLuckyDraw
        prizeDrew?.let {prizeDrew ->

//            necessary?
            prizeDrew.deductStock()
            prizeRepository.updatePrizeStock(prizeDrew)

//            necessary?
            luckyDrawInfo.addEntry()
            luckyDrawRepository.updateLuckyDrawEntry(luckyDrawInfo)

            val userLuckyDraw = UserLuckyDraw(luckyDrawId = luckyDrawId, userId = userId, prizeId = prizeDrew.id)
            userLuckyDrawRepository.saveUserLuckyDraw(userLuckyDraw)

            updateLuckyDrawStatus(luckyDrawId)
            return DrawLuckyDrawResponse(luckyDrawId, prizeDrew.name)
        }
//        !!代替?.let
        return null
    }

    private fun updateLuckyDrawStatus(luckyDrawId: BigInteger) {
        val prizes = prizeRepository.findPrizesByLuckyDrawId(luckyDrawId)
        val totalStock = drawUtil.getTotalStock(prizes)
        if (totalStock == 0) {
            luckyDrawRepository.updateLuckyDrawStatus(luckyDrawId, ENDED)
        }
        val entryIsFull = luckyDrawRepository.retrieveLuckyDrawById(luckyDrawId).isEntryFull()
        if (entryIsFull) {
            luckyDrawRepository.updateLuckyDrawStatus(luckyDrawId, FULL)
        }
    }
}
