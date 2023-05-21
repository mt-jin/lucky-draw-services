package com.example.luckydrawservices.userluckydraw.infrastructure

import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.Table
import java.math.BigInteger
import java.sql.Timestamp

@Entity
@Table(name = "user_lucky_draw")
data class UserLuckyDrawEntity(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY, generator = "native")
    val id: BigInteger?,
    val luckyDrawId: BigInteger,
    val userId: BigInteger,
    val startTime: Timestamp?,
    val endTime: Timestamp?,
    var completed: Boolean?,
    val prizeId: BigInteger
)
