package com.example.luckydrawservices

import org.junit.jupiter.api.Test
import org.springframework.boot.test.context.SpringBootTest


//@DBRider
//@TestInstance(TestInstance.Lifecycle.PER_CLASS)
//@ActiveProfiles("api-test")
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
//@AutoConfigureWireMock(port = 0, files = ["classpath:/stubs"])
//@AutoConfigureWireMock(port = 0)

class BaseApiTest {
//    @LocalServerPort
//    private var port = 8888

//    @Autowired
//    private lateinit var wireMockServer: WireMockServer
//
//    @BeforeEach
//    fun setup() {
//        RestAssured.basePath = "/"
//        RestAssured.requestSpecification = RequestSpecBuilder()
//            .setPort(port)
//            .setContentType(ContentType.JSON)
//            .setAccept(ContentType.JSON)
//            .build()
//    }
//
//    @AfterEach
//    fun clearUp() {
//        wireMockServer.resetRequests()
//    }
    @Test
    fun test(){

    println("hello")
    }
}