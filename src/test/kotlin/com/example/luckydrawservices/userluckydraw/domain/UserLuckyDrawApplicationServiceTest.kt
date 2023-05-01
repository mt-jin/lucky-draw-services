package com.example.luckydrawservices.userluckydraw.domain

import com.example.luckydrawservices.prize.query.model.PrizeInfo
import com.example.luckydrawservices.prize.query.repository.PrizeRepository
import com.example.luckydrawservices.userluckydraw.domain.handler.DrawHandler
import com.example.luckydrawservices.userluckydraw.query.model.UserLuckyDraw
import com.example.luckydrawservices.userluckydraw.query.repository.UserLuckyDrawRepository
import io.mockk.every
import io.mockk.impl.annotations.InjectMockKs
import io.mockk.impl.annotations.MockK
import io.mockk.junit5.MockKExtension
import java.math.BigInteger
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith

@ExtendWith(MockKExtension::class)
class UserLuckyDrawApplicationServiceTest {
    @InjectMockKs
    private lateinit var userLuckyDrawApplicationService: UserLuckyDrawApplicationService

    @MockK
    private lateinit var prizeRepository: PrizeRepository

    @MockK
    private lateinit var userLuckyDrawRepository: UserLuckyDrawRepository

    @MockK
    private lateinit var drawHandler: DrawHandler

    @Test
    fun `should draw lucky draw successfully`() {
        val luckyDrawId = BigInteger.ONE
        val userId = BigInteger.ONE
        val prizeInfo = PrizeInfo(BigInteger.ONE, luckyDrawId, "test prize", BigInteger.TEN)
        every {
            prizeRepository.findPrizesByLuckyDrawId(luckyDrawId)
        } returns listOf(prizeInfo)

        every {
            drawHandler.getPrizeByRandom(listOf(prizeInfo))
        } returns prizeInfo

        every {
            prizeRepository.retrievePrizeById(prizeInfo.id)
        } returns prizeInfo

        every {
            prizeRepository.updatePrizeStock(any())
        } returns prizeInfo.copy(stock = prizeInfo.stock.minus(BigInteger.ONE))


        every {
            userLuckyDrawRepository.saveUserLuckyDraw(any())
        } returns UserLuckyDraw(luckyDrawId, userId, prizeInfo.id)

        val drawLuckyDrawResponse = userLuckyDrawApplicationService.drawLuckyDraw(luckyDrawId, BigInteger.ONE)
        Assertions.assertEquals(luckyDrawId, drawLuckyDrawResponse?.luckyDrawId)
        Assertions.assertEquals("test prize", drawLuckyDrawResponse?.prizeName)
    }
}