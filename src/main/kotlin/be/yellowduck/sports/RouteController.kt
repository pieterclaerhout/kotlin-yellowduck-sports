package be.yellowduck.sports

import be.yellowduck.sports.model.RouteRepository
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.ui.set
import org.springframework.web.bind.annotation.GetMapping

@Controller
class RouteController(private val repository: RouteRepository) {

    @GetMapping("/")
    fun all(model: Model): String {

        val routes = repository.findAllByOrderByChangedAtDesc()

        model["title"] = "My Routes"
        model["routes"] = routes
        model["totalCount"] = routes.count()

        return "routes"

    }

}
