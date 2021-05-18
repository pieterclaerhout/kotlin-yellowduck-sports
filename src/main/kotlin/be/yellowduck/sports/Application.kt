package be.yellowduck.sports

import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication

@SpringBootApplication
class Application {
//
//	@Bean
//	fun demo(repository: RouteRepository): CommandLineRunner? {
//		return CommandLineRunner { _: Array<String?>? ->
//
//			var route: Route = repository.findById(69438185).orElseThrow()
//			println("${route.id}, ${route.name}, ${route.sport}")
//
//			var routes: List<Route> = repository.findAllBySportOrderByChangedAtDesc("mtb_easy").toList()
//			routes.forEach {
//				println("${it.id}, ${it.name}, ${it.sport}")
//			}
//
//			var sports: List<Summary> = repository.findSportsWithCount().toList()
//			sports.forEach {
//				println("${it.name} -> ${it.count}")
//			}
//
//			var calculatedRouteCount: Long = 0
//			sports.forEach { calculatedRouteCount += it.count }
//
//			var routeCount: Long = repository.findTotalRouteCount(33896215)
//			println("Total number of routes: ${routeCount} vs ${calculatedRouteCount}")
//
//		}
//	}

//	@Bean
//	fun commandLineRunner(ctx: ApplicationContext): CommandLineRunner {
//		return CommandLineRunner { _: Array<String?>? ->
////			println("Let's inspect the beans provided by Spring Boot:")
////			val beanNames = ctx.beanDefinitionNames
////			Arrays.sort(beanNames)
////			for (beanName in beanNames) {
////				println(beanName)
////			}
//		}
//	}

	companion object {
		@JvmStatic
		fun main(args: Array<String>) {
			SpringApplication.run(Application::class.java, *args)
		}
	}

}

//fun main(args: Array<String>) {
//	SpringApplication.run(Application::class.java, *args)
//}
