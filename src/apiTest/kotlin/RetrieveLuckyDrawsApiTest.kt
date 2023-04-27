
import com.github.database.rider.core.api.dataset.DataSet
import io.restassured.RestAssured.`when`
import org.hamcrest.CoreMatchers.equalTo
import org.junit.jupiter.api.Test

class RetrieveLuckyDrawsApiTest : ApiTest() {
    @Test
    @DataSet("datasets/lucky_draws_for_retrieve.yml, datasets/prizes_for_retrieve.yml")
    fun `should return 200 when retrieve lucky draws success`() {
        `when`()
            .get("/luckydraws")
            .then().assertThat()
            .statusCode(200)
            .body("[0].id", equalTo(1))
            .body("[0].name", equalTo("Lucky draw A"))
            .body("[0].description", equalTo("Get your prizes now!"))
            .body("[0].maxEntries", equalTo(100))
            .body("[0].entryNumber", equalTo(0))
            .body("[0].prizeNames", equalTo(listOf("pepsi","coca cola")))
    }
}