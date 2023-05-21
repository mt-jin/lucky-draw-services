package com.example.luckydrawservices.luckydraw.query.repository

import com.example.luckydrawservices.luckydraw.infrastructure.LuckyDrawEntity
import com.example.luckydrawservices.luckydraw.infrastructure.LuckyDrawJpaRepository
import com.example.luckydrawservices.luckydraw.infrastructure.LuckyDrawRepositoryImp
import com.example.luckydrawservices.prize.domain.repository.PrizeRepository
import io.mockk.every
import io.mockk.impl.annotations.InjectMockKs
import io.mockk.impl.annotations.MockK
import io.mockk.junit5.MockKExtension
import java.math.BigInteger
import java.time.LocalDateTime
import java.time.Period
import java.util.Optional
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith

@ExtendWith(MockKExtension::class)
class LuckyDrawRepositoryImpTest {
    @InjectMockKs
    private lateinit var luckyDrawRepositoryImp: LuckyDrawRepositoryImp

    @MockK
    private lateinit var jpaRepository: LuckyDrawJpaRepository

    @MockK
    private lateinit var prizeRepository: PrizeRepository

    @Test
    fun `should retrieve lucky draws successfully`() {
        val luckyDrawEntity = LuckyDrawEntity(
            BigInteger.ONE,
            "test lucky draw",
            "test lucky draw",
            BigInteger.TEN,
            BigInteger.ONE,
            startTime = LocalDateTime.now().minus(Period.ofDays(1)),
            endTime = LocalDateTime.now().plus(Period.ofDays(1)),
            userEntryLimit = null
        )
        every {
            jpaRepository.findAllByStatus()
        } returns listOf(luckyDrawEntity)

        val results = luckyDrawRepositoryImp.retrieveActiveLuckyDraws()

        Assertions.assertEquals(1, results.size)
        Assertions.assertEquals(luckyDrawEntity.id, results[0].id)
        Assertions.assertEquals(luckyDrawEntity.name, results[0].name)
        Assertions.assertEquals(luckyDrawEntity.description, results[0].description)
        Assertions.assertEquals(luckyDrawEntity.totalEntryLimit, results[0].totalEntryLimit)
        Assertions.assertEquals(luckyDrawEntity.totalEntryNumber, results[0].totalEntryNumber)
    }

    @Test
    fun `should retrieve lucky draws by id successfully`() {
        val luckyDrawId = BigInteger.ONE
        val luckyDrawEntity = LuckyDrawEntity(
            luckyDrawId,
            "test lucky draw",
            "test lucky draw",
            BigInteger.TEN,
            BigInteger.ONE,
            startTime = LocalDateTime.now().minus(Period.ofDays(1)),
            endTime = LocalDateTime.now().plus(Period.ofDays(1)),
            userEntryLimit = null
        )

        every {
            jpaRepository.findById(luckyDrawId)
        } returns Optional.of(luckyDrawEntity)

        val result = luckyDrawRepositoryImp.retrieveLuckyDrawById(luckyDrawId)

        Assertions.assertEquals(luckyDrawEntity.id, result.id)
        Assertions.assertEquals(luckyDrawEntity.name, result.name)
        Assertions.assertEquals(luckyDrawEntity.description, result.description)
        Assertions.assertEquals(luckyDrawEntity.totalEntryLimit, result.totalEntryLimit)
        Assertions.assertEquals(luckyDrawEntity.totalEntryNumber, result.totalEntryNumber)
    }
}