package com.example.luckydrawservices.userluckydraw.infrastructure

import jakarta.persistence.Entity
import jakarta.persistence.Id
import jakarta.persistence.Table
import java.math.BigInteger
import java.sql.Timestamp

@Entity
@Table(name = "user_lucky_draw")
data class UserLuckyDrawEntity(
    @Id
    val id: BigInteger,
    val luckyDrawId: BigInteger,
    val userId: BigInteger,
    val startTime: Timestamp?,
    val endTime: Timestamp?,
    val completed: Boolean?,
    val prizeId: BigInteger
) {

}
