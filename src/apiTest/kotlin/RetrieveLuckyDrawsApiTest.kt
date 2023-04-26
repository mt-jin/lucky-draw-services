
import io.restassured.RestAssured.`when`
import org.junit.jupiter.api.Test

class RetrieveLuckyDrawsApiTest : ApiTest() {
    @Test
//    @DataSet("datasets/lucky_draws_for_retrieve.yml", "datasets/prizes_for_retrieve.yml")
    fun `should return 200 when retrieve lucky draws success`() {
        `when`()
            .get("/luckydraws")
            .then().assertThat().statusCode(200)

    }

    @Test
    fun `should success when init success`() {
        val test = 42
    }
}