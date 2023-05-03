package com.example.luckydrawservices.userluckydraw.domain

import com.example.luckydrawservices.common.exception.LuckyDrawStatusException
import com.example.luckydrawservices.luckydraw.infrastructure.LuckyDrawEntity
import com.example.luckydrawservices.luckydraw.infrastructure.LuckyDrawStatus
import com.example.luckydrawservices.luckydraw.query.model.LuckyDrawInfo
import com.example.luckydrawservices.luckydraw.query.repository.LuckyDrawRepository
import com.example.luckydrawservices.prize.query.model.PrizeInfo
import com.example.luckydrawservices.prize.query.repository.PrizeRepository
import com.example.luckydrawservices.userluckydraw.domain.Utils.DrawUtil
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
    private lateinit var luckyDrawRepository: LuckyDrawRepository

    @MockK
    private lateinit var drawUtil: DrawUtil

    @Test
    fun `should draw lucky draw successfully`() {
        val luckyDrawId = BigInteger.ONE
        val userId = BigInteger.ONE
        val luckyDrawInfo = LuckyDrawInfo(BigInteger.ONE, "test lucky draw", "test lucky draw", BigInteger.TEN, BigInteger.ONE, LuckyDrawStatus.ACTIVE)
        val luckyDrawEntity = LuckyDrawEntity(BigInteger.ONE, "test lucky draw", "test lucky draw", BigInteger.TEN, BigInteger.ONE, "test mode", "test categories", "test tags", LuckyDrawStatus.ACTIVE, 0)
        val prizeInfo = PrizeInfo(BigInteger.ONE, luckyDrawId, "test prize", BigInteger.TEN)
        every {
            luckyDrawRepository.retrieveLuckyDrawById(luckyDrawId)
        } returns luckyDrawInfo

        every {
            prizeRepository.findPrizesByLuckyDrawId(luckyDrawId)
        } returns listOf(prizeInfo)

        every {
            drawUtil.getPrizeByRandom(listOf(prizeInfo))
        } returns prizeInfo

        every {
            prizeRepository.retrievePrizeById(prizeInfo.id)
        } returns prizeInfo

        every {
            prizeRepository.updatePrizeStock(any())
        } returns prizeInfo.copy(stock = prizeInfo.stock.minus(BigInteger.ONE))


        every {
            luckyDrawRepository.updateLuckyDrawEntry(luckyDrawInfo)
        } returns luckyDrawEntity


        every {
            userLuckyDrawRepository.saveUserLuckyDraw(any())
        } returns UserLuckyDraw(luckyDrawId, userId, prizeInfo.id)

        every { drawUtil.getTotalStock(listOf(prizeInfo)) } returns 10

        val drawLuckyDrawResponse = userLuckyDrawApplicationService.drawLuckyDraw(luckyDrawId, BigInteger.ONE)
        Assertions.assertEquals(luckyDrawId, drawLuckyDrawResponse?.luckyDrawId)
        Assertions.assertEquals("test prize", drawLuckyDrawResponse?.prizeName)
    }

    @Test
    fun `should throw exception given lucky draw status is ended`() {
        val luckyDrawId = BigInteger.ONE
        val luckyDrawInfo = LuckyDrawInfo(BigInteger.ONE, "test lucky draw", "test lucky draw", BigInteger.TEN, BigInteger.ONE, LuckyDrawStatus.ENDED)
        every {
            luckyDrawRepository.retrieveLuckyDrawById(luckyDrawId)
        } returns luckyDrawInfo
        Assertions.assertThrows(LuckyDrawStatusException::class.java) {
            userLuckyDrawApplicationService.drawLuckyDraw(luckyDrawId, BigInteger.ONE)
        }
    }

    @Test
    fun `should throw exception given lucky draw status is full`() {
        val luckyDrawId = BigInteger.ONE
        val luckyDrawInfo = LuckyDrawInfo(BigInteger.ONE, "test lucky draw", "test lucky draw", BigInteger.TEN, BigInteger.ONE, LuckyDrawStatus.FULL)
        every {
            luckyDrawRepository.retrieveLuckyDrawById(luckyDrawId)
        } returns luckyDrawInfo
        Assertions.assertThrows(LuckyDrawStatusException::class.java) {
            userLuckyDrawApplicationService.drawLuckyDraw(luckyDrawId, BigInteger.ONE)
        }
    }


}