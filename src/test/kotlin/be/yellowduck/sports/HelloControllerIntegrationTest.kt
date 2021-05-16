package be.yellowduck.sports

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.web.client.TestRestTemplate
import org.springframework.boot.web.server.LocalServerPort
import java.net.URL

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class HelloControllerIntegrationTest(@Autowired val template: TestRestTemplate) {

    @LocalServerPort
    private val port = 0
    private var base: URL? = null

    @BeforeEach
    fun setUp() {
        base = URL("http://localhost:$port/hello")
    }

    @Test
    fun hello() {
        val response = template.getForEntity(
            base.toString(),
            String::class.java
        )
        assertThat(response.body).isEqualTo("Greetings from Spring Boot!")
    }

}