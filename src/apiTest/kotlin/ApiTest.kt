
import com.example.luckydrawservices.LuckyDrawServicesApplication
import com.github.database.rider.junit5.api.DBRider
import io.restassured.RestAssured
import io.restassured.builder.RequestSpecBuilder
import io.restassured.http.ContentType
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.TestInstance
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.web.server.LocalServerPort
import org.springframework.test.context.ActiveProfiles


@DBRider
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@ActiveProfiles("api-test")
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT, classes = [LuckyDrawServicesApplication::class])

class ApiTest {
    @LocalServerPort
    private var port = 8889

    @BeforeEach
    fun setup() {
        RestAssured.basePath = "/"
        RestAssured.requestSpecification = RequestSpecBuilder()
            .setPort(port)
            .setContentType(ContentType.JSON)
            .setAccept(ContentType.JSON)
            .build()
    }

}