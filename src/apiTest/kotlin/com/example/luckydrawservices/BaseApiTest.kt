package com.example.luckydrawservices

import com.github.database.rider.junit5.api.DBRider
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.TestInstance
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.cloud.contract.wiremock.AutoConfigureWireMock
import org.springframework.test.context.ActiveProfiles


@DBRider
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@ActiveProfiles("api-test")
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureWireMock
class BaseApiTest {
    @Test
    fun test(){
        println("Api test")
    }
}