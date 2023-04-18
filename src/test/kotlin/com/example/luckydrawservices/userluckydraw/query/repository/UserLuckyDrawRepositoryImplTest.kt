package com.example.luckydrawservices.userluckydraw.query.repository

import com.example.luckydrawservices.userluckydraw.infrastructure.UserLuckyDrawEntity
import com.example.luckydrawservices.userluckydraw.query.model.UserLuckyDraw
import io.mockk.every
import io.mockk.impl.annotations.InjectMockKs
import io.mockk.impl.annotations.MockK
import io.mockk.junit5.MockKExtension
import io.mockk.verify
import java.math.BigInteger
import java.time.Instant
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith

@ExtendWith(MockKExtension::class)
class UserLuckyDrawRepositoryImplTest {

    @InjectMockKs
    private lateinit var userLuckyDrawRepositoryImpl: UserLuckyDrawRepositoryImpl

    @MockK
    private lateinit var userLuckyDrawJpaRepository: UserLuckyDrawJpaRepository

    private val userId = BigInteger.valueOf(1L)

    private val luckyDrawId = BigInteger.valueOf(2L)

    private val prizeId = BigInteger.valueOf(3L)

    private val now = Instant.now()

    private val completed = true

    private val userLuckyDrawEntity = UserLuckyDrawEntity(
        null, luckyDrawId, userId, null, null, completed, prizeId
    )

    private val userLuckyDraw = UserLuckyDraw(
        luckyDrawId, userId, prizeId
    )

    @Test
    fun `should retrieve user lucky draws by user id`() {
        every {
            userLuckyDrawJpaRepository.findALlByUserId(userId)
        } returns listOf(userLuckyDrawEntity)

        val userLuckyDraws = userLuckyDrawRepositoryImpl.retrieveUserLuckyDrawsByUserId(userId)

        Assertions.assertEquals(listOf(userLuckyDraw), userLuckyDraws)
    }

    @Test
    fun `should save user lucky draw successfully`() {
        val completedUserLuckyDrawEntity = userLuckyDrawEntity.copy(completed = true)
        every {
            userLuckyDrawJpaRepository.saveAndFlush(completedUserLuckyDrawEntity)
        } returns completedUserLuckyDrawEntity

        userLuckyDrawRepositoryImpl.saveUserLuckyDraw(userLuckyDraw)

        verify(exactly = 1) {
            userLuckyDrawJpaRepository.saveAndFlush(completedUserLuckyDrawEntity)
        }
    }

}
