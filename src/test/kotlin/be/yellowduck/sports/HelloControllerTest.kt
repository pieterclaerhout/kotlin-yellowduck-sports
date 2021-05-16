package be.yellowduck.sports

import org.hamcrest.Matchers
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.http.MediaType
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.content
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.status

@SpringBootTest
@AutoConfigureMockMvc
class HelloControllerTest(@Autowired val mvc: MockMvc) {

    @Test
    fun hello() {
        mvc.perform(get("/hello").accept(MediaType.APPLICATION_JSON))
           .andExpect(status().isOk)
           .andExpect(content().string(Matchers.equalTo("Greetings from Spring Boot!")))
    }

}