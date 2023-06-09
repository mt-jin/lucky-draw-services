package com.example.luckydrawservices.luckydraw.query

import com.example.luckydrawservices.luckydraw.domain.repository.LuckyDrawRepository
import com.example.luckydrawservices.luckydraw.infrastructure.LuckyDrawStatus
import com.example.luckydrawservices.luckydraw.query.model.LuckyDrawInfo
import com.example.luckydrawservices.luckydraw.query.response.LuckyDrawInfoResponse
import com.example.luckydrawservices.prize.domain.repository.PrizeRepository
import com.example.luckydrawservices.prize.query.model.PrizeInfo
import io.mockk.every
import io.mockk.impl.annotations.InjectMockKs
import io.mockk.impl.annotations.MockK
import io.mockk.junit5.MockKExtension
import java.math.BigInteger
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith

@ExtendWith(MockKExtension::class)
class LuckyDrawQueryServiceTest{


    @InjectMockKs
    private lateinit var luckyDrawQueryService: LuckyDrawQueryService

    @MockK
    private lateinit var prizeRepository: PrizeRepository

    @MockK
    private lateinit var repository: LuckyDrawRepository

    @Test
    fun `should return all lucky draws in a list successfully`(){
        every {
            repository.retrieveActiveLuckyDraws()
        }returns listOf(luckyDrawInfo)

        every {
            prizeRepository.findPrizesByLuckyDrawId(BigInteger.ONE)
        }returns listOf(prizeInfoCola, prizeInfoWater, prizeInfoOneHundredDollars)

        val luckyDraw = luckyDrawQueryService.retrieveActiveLuckyDraws()[0]

        Assertions.assertEquals(luckyDrawInfoResponse.id, luckyDraw.id)
        Assertions.assertEquals(luckyDrawInfoResponse.description, luckyDraw.description)
        Assertions.assertEquals(luckyDrawInfoResponse.name, luckyDraw.name)
        Assertions.assertEquals(luckyDrawInfoResponse.totalEntryNumber, luckyDraw.totalEntryNumber)
        Assertions.assertEquals(luckyDrawInfoResponse.totalEntryLimit, luckyDraw.totalEntryLimit)
        Assertions.assertEquals(luckyDrawInfoResponse.prizeNames, luckyDraw.prizeNames)

    }


    val luckyDrawInfoResponse = LuckyDrawInfoResponse(
        BigInteger.ONE,
        "Lucky Draw test",
        "Lucky Draw test",
        BigInteger.ZERO,
        BigInteger.ZERO,
        listOf("Cola", "Water", "$100")
    )

    val luckyDrawInfo = LuckyDrawInfo(
        BigInteger.ONE,
        "Lucky Draw test",
        "Lucky Draw test",
        BigInteger.ZERO,
        BigInteger.ZERO,
        LuckyDrawStatus.ACTIVE
    )

    val prizeInfoCola = PrizeInfo(
        BigInteger.ZERO,
        BigInteger.ONE,
        "Cola",
        BigInteger.ONE
    )

    val prizeInfoWater = PrizeInfo(
        BigInteger.ZERO,
        BigInteger.ONE,
        "Water",
        BigInteger.ONE
    )


    val prizeInfoOneHundredDollars = PrizeInfo(
        BigInteger.ZERO,
        BigInteger.ONE,
        "$100",
        BigInteger.ONE
    )

    @Test
    fun `should return empty list given no lucky draw exists`() {
        every { repository.retrieveActiveLuckyDraws() } returns emptyList()

        val luckyDraws = luckyDrawQueryService.retrieveActiveLuckyDraws()

        Assertions.assertTrue(luckyDraws.isEmpty())
    }
}