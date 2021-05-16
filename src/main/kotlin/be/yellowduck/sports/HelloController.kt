package be.yellowduck.sports

import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class HelloController {

	@RequestMapping("/hello")
	fun index(): String {
		return "Greetings from Spring Boot!"
	}

}
