package com.example.luckydrawservices.userluckydraw.domain

import com.example.luckydrawservices.common.exception.conflict.LuckyDrawNotActiveException
import com.example.luckydrawservices.common.exception.internalservererror.RandomProcessException
import com.example.luckydrawservices.common.exception.notfound.PrizesNotFoundException
import com.example.luckydrawservices.luckydraw.domain.repository.LuckyDrawRepository
import com.example.luckydrawservices.userluckydraw.domain.response.DrawLuckyDrawResponse
import com.example.luckydrawservices.userluckydraw.domain.utils.DrawUtil
import com.example.luckydrawservices.userluckydraw.query.model.UserLuckyDraw
import com.example.luckydrawservices.userluckydraw.query.repository.UserLuckyDrawRepository
import java.math.BigInteger
import org.springframework.stereotype.Service


@Service
class UserLuckyDrawApplicationService(
    val userLuckyDrawRepository: UserLuckyDrawRepository,
    val luckyDrawRepository: LuckyDrawRepository,
    val drawUtil: DrawUtil
) {
    fun drawLuckyDraw(luckyDrawId: BigInteger, userId: BigInteger): DrawLuckyDrawResponse? {

        val luckyDrawWithItems = luckyDrawRepository.retrieveLuckyDrawWithItems(luckyDrawId)
        if (!luckyDrawWithItems.isActive()) {
            throw LuckyDrawNotActiveException("Lucky draw of id [$luckyDrawId] is not active")
        }
        if (luckyDrawWithItems.itemsIsEmpty()) {
            throw PrizesNotFoundException("Prizes of Lucky draw with id [$luckyDrawId] not found in database")
        }
        val prizeDrew = drawUtil.getPrizeByRandom(luckyDrawWithItems.items)
            ?: throw RandomProcessException("Draw lucky draw failed due to random process error")
        luckyDrawWithItems.updateEntryAndStock(prizeDrew)
        luckyDrawWithItems.updateStatus()

        luckyDrawRepository.updateLuckyDrawWithItems(luckyDrawWithItems)

        UserLuckyDraw(luckyDrawId, userId, prizeDrew.id).let { userLuckyDrawRepository.saveUserLuckyDraw(it) }

        return DrawLuckyDrawResponse(luckyDrawId, prizeDrew.name)
    }
}
