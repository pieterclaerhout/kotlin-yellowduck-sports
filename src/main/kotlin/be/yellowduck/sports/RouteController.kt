package be.yellowduck.sports

import be.yellowduck.sports.model.RouteRepository
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.*
import org.springframework.web.server.ResponseStatusException

@RestController
@RequestMapping("/api/route")
class RouteController(private val repository: RouteRepository) {

    @GetMapping("/all")
    fun findAll() = repository.findAllByOrderByChangedAtDesc()

    @GetMapping("/{id}")
    fun findOne(@PathVariable id: Long) =
        repository.findById(id).or {
            throw ResponseStatusException(HttpStatus.NOT_FOUND, "This route does not exist")
        }

    @GetMapping("/{id}/gpx", produces = arrayOf("application/gpx+xml"))
    fun findOneGPX(@PathVariable id: Long) =
        repository.findById(id).or {
            throw ResponseStatusException(HttpStatus.NOT_FOUND, "This route does not exist")
        }.get().toGPX()

}
