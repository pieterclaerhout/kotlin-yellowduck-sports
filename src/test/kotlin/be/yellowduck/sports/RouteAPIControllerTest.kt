package be.yellowduck.sports

import be.yellowduck.gpx.Polyline
import be.yellowduck.gpx.TrackPoint
import be.yellowduck.sports.model.Route
import be.yellowduck.sports.model.RouteRepository
import com.ninjasquad.springmockk.MockkBean
import io.mockk.every
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest
import org.springframework.http.MediaType
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.*
import java.time.LocalDateTime
import java.time.ZoneOffset
import java.util.*

@WebMvcTest
class RouteAPIControllerTest(@Autowired val mockMvc: MockMvc) {

    @MockkBean
    private lateinit var routeRepository: RouteRepository

    val now = LocalDateTime.ofEpochSecond(1621174415, 0, ZoneOffset.UTC)
    val polyline = Polyline.encode(
        listOf(
            TrackPoint(lon = 16.34528, lat = 48.1969),
            TrackPoint(lon = 16.34725, lat = 48.19732),
            TrackPoint(lon = 16.40988, lat = 48.22858)
        )
    )

    @BeforeEach
    fun setup() {
        val route1 = Route(1, 1234, "Route 1", "hike", changedAt = now, polyline = polyline)
        val route2 = Route(2, 1234, "Route 2", "mtb", changedAt = now, polyline = polyline)
        every { routeRepository.findAllByOrderByChangedAtDesc() } returns listOf(route1, route2)
        every { routeRepository.findById(1) } returns Optional.of(route1)
        every { routeRepository.findById(1234) } returns Optional.empty()
    }

    @Test
    fun all() {
        mockMvc.perform(get("/api/route/all").accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isOk)
            .andExpect(content().contentType(MediaType.APPLICATION_JSON))
            .andExpect(jsonPath("\$.[0].id").value(1))
            .andExpect(jsonPath("\$.[0].name").value("Route 1"))
            .andExpect(jsonPath("\$.[0].token_id").value(1234))
            .andExpect(jsonPath("\$.[0].sport").value("hike"))
            .andExpect(jsonPath("\$.[0].changed_at").value(now.toString()))
            .andExpect(jsonPath("\$.[1].id").value(2))
            .andExpect(jsonPath("\$.[1].name").value("Route 2"))
            .andExpect(jsonPath("\$.[1].token_id").value(1234))
            .andExpect(jsonPath("\$.[1].sport").value("mtb"))
            .andExpect(jsonPath("\$.[1].changed_at").value(now.toString()))
    }

    @Test
    fun oneExisting() {
        mockMvc.perform(get("/api/route/1").accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isOk)
            .andExpect(content().contentType(MediaType.APPLICATION_JSON))
            .andExpect(jsonPath("\$.id").value(1))
            .andExpect(jsonPath("\$.name").value("Route 1"))
            .andExpect(jsonPath("\$.token_id").value(1234))
            .andExpect(jsonPath("\$.sport").value("hike"))
            .andExpect(jsonPath("\$.changed_at").value(now.toString()))
    }

    @Test
    fun oneExistingGPX() {
        mockMvc.perform(get("/api/route/1/gpx").accept("*"))
            .andExpect(status().isOk)
            .andExpect(content().contentType("application/gpx+xml;charset=UTF-8"))
            .andExpect(content().string("""<?xml version="1.0" ?>
<gpx version="1.1" creator="sports.yellowduck.be" xmlns="http://www.topografix.com/GPX/1/1">
  <metadata>
    <name>Route 1</name>
  </metadata>
  <trk>
    <name>Route 1</name>
    <trkseg>
      <trkpt lat="48.1969" lon="16.34528"></trkpt>
      <trkpt lat="48.19732" lon="16.34725"></trkpt>
      <trkpt lat="48.22858" lon="16.40987"></trkpt>
    </trkseg>
  </trk>
</gpx>"""))
    }

    @Test
    fun oneNotExisting() {
        mockMvc.perform(get("/api/route/1234").accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNotFound)
    }

}
