package com.example.luckydrawservices.userluckydraw.domain

import com.example.luckydrawservices.common.exception.ApplicationException
import com.example.luckydrawservices.common.exception.conflict.LuckyDrawHasEndedException
import com.example.luckydrawservices.common.exception.conflict.LuckyDrawNotActiveException
import com.example.luckydrawservices.common.exception.conflict.LuckyDrawNotStartedException
import com.example.luckydrawservices.fixture.LuckyDrawFixture.luckyDrawWithItems
import com.example.luckydrawservices.luckydraw.domain.repository.LuckyDrawRepository
import com.example.luckydrawservices.luckydraw.infrastructure.LuckyDrawStatus
import com.example.luckydrawservices.userluckydraw.domain.utils.DrawUtil
import com.example.luckydrawservices.userluckydraw.query.model.UserLuckyDraw
import com.example.luckydrawservices.userluckydraw.query.repository.UserLuckyDrawRepository
import io.mockk.every
import io.mockk.impl.annotations.InjectMockKs
import io.mockk.impl.annotations.MockK
import io.mockk.junit5.MockKExtension
import io.mockk.justRun
import java.math.BigInteger
import java.time.LocalDateTime
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.MethodSource

@ExtendWith(MockKExtension::class)
class UserLuckyDrawApplicationServiceTest {
    @InjectMockKs
    private lateinit var userLuckyDrawApplicationService: UserLuckyDrawApplicationService

    @MockK
    private lateinit var userLuckyDrawRepository: UserLuckyDrawRepository

    @MockK
    private lateinit var luckyDrawRepository: LuckyDrawRepository

    private val drawUtil = DrawUtil()

    @Test
    fun `should draw lucky draw successfully`() {
        val luckyDrawId = BigInteger.ONE
        val userId = BigInteger.ONE
        val prizeId = BigInteger.ONE

        every {
            luckyDrawRepository.retrieveLuckyDrawWithItems(luckyDrawId)
        } returns luckyDrawWithItems


        justRun {
            luckyDrawRepository.updateLuckyDrawWithItems(any())
        }

        every {
            userLuckyDrawRepository.saveUserLuckyDraw(any())
        } returns UserLuckyDraw(luckyDrawId, userId, prizeId)


        val drawLuckyDrawResponse = userLuckyDrawApplicationService.drawLuckyDraw(luckyDrawId, BigInteger.ONE)
        Assertions.assertEquals(luckyDrawId, drawLuckyDrawResponse?.luckyDrawId)
        Assertions.assertEquals("test prize", drawLuckyDrawResponse?.prizeName)
    }

    @Test
    fun `should throw exception given lucky draw status is ended`() {
        val luckyDrawId = BigInteger.ONE

        every {
            luckyDrawRepository.retrieveLuckyDrawWithItems(luckyDrawId)
        } returns luckyDrawWithItems.copy(status = LuckyDrawStatus.ENDED)

        Assertions.assertThrows(LuckyDrawNotActiveException::class.java) {
            userLuckyDrawApplicationService.drawLuckyDraw(luckyDrawId, BigInteger.ONE)
        }
    }

    @Test
    fun `should throw exception given lucky draw status is full`() {
        val luckyDrawId = BigInteger.ONE

        every {
            luckyDrawRepository.retrieveLuckyDrawWithItems(luckyDrawId)
        } returns luckyDrawWithItems.copy(status = LuckyDrawStatus.FULL)

        Assertions.assertThrows(LuckyDrawNotActiveException::class.java) {
            userLuckyDrawApplicationService.drawLuckyDraw(luckyDrawId, BigInteger.ONE)
        }
    }


    @ParameterizedTest
    @MethodSource("timeProvider")
    fun `should throw exception given lucky draw time not valid`(
        startTime: LocalDateTime, endTime: LocalDateTime, expected: ApplicationException
    ) {
        val luckyDrawId = BigInteger.ONE

        every {
            luckyDrawRepository.retrieveLuckyDrawWithItems(luckyDrawId)
        } returns luckyDrawWithItems.copy(startTime = startTime, endTime = endTime)

        Assertions.assertThrows(expected.javaClass) {
            userLuckyDrawApplicationService.drawLuckyDraw(luckyDrawId, BigInteger.ONE)
        }
    }

    @ParameterizedTest
    @MethodSource("entryProvider")
    fun `should throw exception given lucky draw entry not valid`(
        totalEntryLimit: BigInteger, entry: BigInteger, expected: ApplicationException
    ) {
        val luckyDrawId = BigInteger.ONE

        every {
            luckyDrawRepository.retrieveLuckyDrawWithItems(luckyDrawId)
        } returns luckyDrawWithItems.copy(maxEntries = totalEntryLimit, entryNumber = entry)

        Assertions.assertThrows(expected.javaClass) {
            userLuckyDrawApplicationService.drawLuckyDraw(luckyDrawId, BigInteger.ONE)
        }
    }

    companion object {

        @JvmStatic
        fun timeProvider(): List<Arguments> {
            return listOf(
                Arguments.arguments(
                    LocalDateTime.now().plusDays(1),
                    LocalDateTime.now().plusDays(2),
                    LuckyDrawNotStartedException("test")
                ), Arguments.arguments(
                    LocalDateTime.now().minusDays(2),
                    LocalDateTime.now().minusDays(1),
                    LuckyDrawHasEndedException("test")
                )
            )
        }

        @JvmStatic
        fun entryProvider(): List<Arguments> {
            return listOf(
                Arguments.arguments(
                    BigInteger.ZERO, BigInteger.ZERO, LuckyDrawNotActiveException("test")
                ),
            )
        }
    }
}
