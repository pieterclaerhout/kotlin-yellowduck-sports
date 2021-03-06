package be.yellowduck.sports

import be.yellowduck.sports.model.RouteRepository
import org.springframework.http.HttpHeaders
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.server.ResponseStatusException

@RestController
@RequestMapping("/api/route")
class RouteAPIController(private val repository: RouteRepository) {

    @GetMapping("/all")
    fun findAll() = repository.findAllByOrderByChangedAtDesc()

    @GetMapping("/{id}")
    fun findOne(@PathVariable id: Long) =
        repository.findById(id).or {
            throw ResponseStatusException(HttpStatus.NOT_FOUND, "This route does not exist")
        }

    @GetMapping(
        "/{id}/gpx",
        produces = arrayOf("application/gpx+xml")
    )
    fun findOneGPX(@PathVariable id: Long) : ResponseEntity<String> {

        val route =repository.findById(id).or {
            throw ResponseStatusException(HttpStatus.NOT_FOUND, "This route does not exist")
        }.get()

        val headers = HttpHeaders()
        headers.add("Content-Disposition", "Attachment; filename=${id}_${route.name.toSlug()}.gpx")

        return ResponseEntity<String>(route.toGPX(), headers, HttpStatus.OK)

    }

}
