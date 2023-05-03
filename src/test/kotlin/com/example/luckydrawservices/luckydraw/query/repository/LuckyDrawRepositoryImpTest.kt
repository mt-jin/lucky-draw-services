package com.example.luckydrawservices.luckydraw.query.repository

import com.example.luckydrawservices.luckydraw.infrastructure.LuckyDrawEntity
import io.mockk.every
import io.mockk.impl.annotations.InjectMockKs
import io.mockk.impl.annotations.MockK
import io.mockk.junit5.MockKExtension
import java.math.BigInteger
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

    @Test
    fun `should retrieve lucky draws successfully`() {
        val luckyDrawEntity = LuckyDrawEntity(
            BigInteger.ONE,
            "test lucky draw",
            "test lucky draw",
            BigInteger.TEN,
            BigInteger.ONE
        )
        every {
            jpaRepository.findAllByStatus()
        } returns listOf(luckyDrawEntity)

        val results = luckyDrawRepositoryImp.retrieveActiveLuckyDraws()

        Assertions.assertEquals(1, results.size)
        Assertions.assertEquals(luckyDrawEntity.id, results[0].id)
        Assertions.assertEquals(luckyDrawEntity.name, results[0].name)
        Assertions.assertEquals(luckyDrawEntity.description, results[0].description)
        Assertions.assertEquals(luckyDrawEntity.maxEntries, results[0].maxEntries)
        Assertions.assertEquals(luckyDrawEntity.entryNumber, results[0].entryNumber)
    }

    @Test
    fun `should retrieve lucky draws by id successfully`() {
        val luckyDrawId = BigInteger.ONE
        val luckyDrawEntity = LuckyDrawEntity(
            luckyDrawId,
            "test lucky draw",
            "test lucky draw",
            BigInteger.TEN,
            BigInteger.ONE
        )

        every {
            jpaRepository.findById(luckyDrawId)
        } returns Optional.of(luckyDrawEntity)

        val result = luckyDrawRepositoryImp.retrieveLuckyDrawById(luckyDrawId)

        Assertions.assertEquals(luckyDrawEntity.id, result.id)
        Assertions.assertEquals(luckyDrawEntity.name, result.name)
        Assertions.assertEquals(luckyDrawEntity.description, result.description)
        Assertions.assertEquals(luckyDrawEntity.maxEntries, result.maxEntries)
        Assertions.assertEquals(luckyDrawEntity.entryNumber, result.entryNumber)
    }
}