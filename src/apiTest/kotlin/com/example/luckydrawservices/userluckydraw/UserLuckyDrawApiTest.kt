package com.example.luckydrawservices.userluckydraw

import ApiTest
import com.github.database.rider.core.api.dataset.DataSet
import io.restassured.RestAssured.`when`
import org.hamcrest.Matchers.equalTo
import org.hamcrest.Matchers.notNullValue
import org.junit.jupiter.api.Test


class UserLuckyDrawApiTest : ApiTest() {
    @Test
    @DataSet("datasets/user_lucky_draws_for_retrieve.yml, datasets/lucky_draws_for_retrieve.yml, datasets/prizes_for_retrieve.yml")
    fun `should return 200 with retrieve user lucky draws info successfully`() {

            `when`()
            .get("/userluckydraws/1")
            .then()
            .assertThat()
            .statusCode(200)
            .body("size()", equalTo(3))
            .body("[0].luckyDrawName", equalTo("Lucky draw A"))
    }


    @Test
//    @DataSet("datasets/user_lucky_draws_for_retrieve.yml, datasets/lucky_draws_for_retrieve.yml, datasets/prizes_for_retrieve.yml")
    @DataSet("datasets/create_user_lucky_draw.sql, datasets/insert_user_lucky_draws.sql, datasets/lucky_draws_for_retrieve.yml, datasets/prizes_for_retrieve.yml")
    fun `should return prize id when draw lucky draw successfully`(){
        `when`()
            .post("/userluckydraws/luckydraws/1/userid/1")
            .then()
            .assertThat()
            .statusCode(200)
            .body("luckyDrawId", equalTo(1))
            .body("prizeName", notNullValue())

    }
}
