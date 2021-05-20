package be.yellowduck.sports

import be.yellowduck.gpx.Distance
import be.yellowduck.sports.model.RouteRepository
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.ui.set
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable

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

    @GetMapping("/by-distance/{min}/{max}")
    fun byDistance(
        model: Model,
        @PathVariable min: Long = 25,
        @PathVariable max: Long = 50
    ): String {

        val minDistance = Distance(min * 1000.0)
        val maxDistance = Distance(max * 1000.0)

        val routes = repository.findAllWithDistanceRange(minDistance.meters, maxDistance.meters)

        model["title"] = "Routes between ${minDistance.formattedKilometers} and ${maxDistance.formattedKilometers}"
        model["routes"] = routes
        model["totalCount"] = routes.count()

        return "routes"

    }

}
