package com.example.luckydrawservices.userluckydraw.query

import com.example.luckydrawservices.luckydraw.query.model.LuckyDrawInfo
import com.example.luckydrawservices.luckydraw.query.repository.LuckyDrawRepository
import com.example.luckydrawservices.prize.query.model.PrizeInfo
import com.example.luckydrawservices.prize.query.repository.PrizeRepository
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
class UserLuckyDrawQueryServiceTest {

    @InjectMockKs
    private lateinit var userLuckyDrawQueryService: UserLuckyDrawQueryService

    @MockK
    private lateinit var luckyDrawRepository: LuckyDrawRepository

    @MockK
    private lateinit var prizeRepository: PrizeRepository

    @MockK
    private lateinit var userLuckyDrawRepository: UserLuckyDrawRepository

    @Test
    fun `should return all user lucky draws in a list successfully`() {
        val userId = BigInteger.ONE
        val luckyDrawId = BigInteger.ONE
        val prizeId = BigInteger.ONE
        val userLuckyDraw = UserLuckyDraw(luckyDrawId,userId,prizeId)
        val prizeInfo = PrizeInfo(prizeId, luckyDrawId, "test prize", BigInteger.TEN)
        val luckyDrawInfo = LuckyDrawInfo(luckyDrawId, "test lucky draw", "test lucky draw", BigInteger.TEN, BigInteger.ONE)

        every {
            userLuckyDrawRepository.retrieveUserLuckyDrawsByUserId(userId)
        } returns listOf(userLuckyDraw)

        every {
            prizeRepository.retrievePrizeById(userLuckyDraw.prizeId)
        } returns prizeInfo

        every {
            luckyDrawRepository.retrieveLuckyDrawById(userLuckyDraw.luckyDrawId)
        } returns luckyDrawInfo

        val responses = userLuckyDrawQueryService.retrieveUserLuckyDrawsByUserId(userId)

        Assertions.assertEquals(1,responses.size)
        Assertions.assertEquals("test lucky draw",responses[0].luckyDrawName)
        Assertions.assertEquals("test prize",responses[0].prizeName)

    }
}