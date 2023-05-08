package com.example.luckydrawservices.userluckydraw.domain

import com.example.luckydrawservices.common.exception.LuckyDrawNotActiveException
import com.example.luckydrawservices.common.exception.PrizeNegativeStockException
import com.example.luckydrawservices.common.exception.PrizesNotFoundException
import com.example.luckydrawservices.common.exception.RandomProcessException
import com.example.luckydrawservices.luckydraw.infrastructure.LuckyDrawStatus.ACTIVE
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
        if (!luckyDrawInfo.isActive()) {
            throw LuckyDrawNotActiveException("Lucky draw of id [$luckyDrawId] is not active")
        }

        val prizes = prizeRepository.findPrizesByLuckyDrawId(luckyDrawId)
            .takeIf { it.isNotEmpty() }
            ?: throw PrizesNotFoundException("Prizes of Lucky draw with id $luckyDrawId not found in database")

        val prizeDrew = drawUtil.getPrizeByRandom(prizes)
            ?: throw RandomProcessException("Draw lucky draw failed due to random process error")

        prizeDrew.also { it.deductStock() }.let { prizeRepository.updatePrizeStock(it) }
        luckyDrawInfo.also { it.addEntry() }.let { luckyDrawRepository.updateLuckyDrawEntry(it) }
        UserLuckyDraw(luckyDrawId, userId, prizeDrew.id).let { userLuckyDrawRepository.saveUserLuckyDraw(it) }

        updateLuckyDrawStatus(luckyDrawId)

        return DrawLuckyDrawResponse(luckyDrawId, prizeDrew.name)

    }

    private fun updateLuckyDrawStatus(luckyDrawId: BigInteger) {

        val entryIsFull = luckyDrawRepository.retrieveLuckyDrawById(luckyDrawId).isEntryFull()
        var luckyDrawStatus = if (entryIsFull) FULL else ACTIVE

        val totalStock = prizeRepository.findPrizesByLuckyDrawId(luckyDrawId)
            .let { drawUtil.getTotalStock(it) }

        luckyDrawStatus = when (totalStock.compareTo(0)) {
            0 -> ENDED
            1 -> luckyDrawStatus
            else -> ENDED.also {
                throw PrizeNegativeStockException("Lucky draw of id [$luckyDrawId] has negative stock prize")
            }
        }

        luckyDrawRepository.updateLuckyDrawStatus(luckyDrawId, luckyDrawStatus)
    }
}
