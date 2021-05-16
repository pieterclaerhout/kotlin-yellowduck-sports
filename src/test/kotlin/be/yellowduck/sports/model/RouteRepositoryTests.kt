package be.yellowduck.sports.model

import be.yellowduck.sports.gpx.TrackPoint
import be.yellowduck.sports.gpx.Polyline
import com.ninjasquad.springmockk.MockkBean
import io.mockk.every
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest
import org.springframework.data.repository.findByIdOrNull
import java.time.LocalDateTime
import java.time.ZoneOffset
import java.util.*

@DataJpaTest
//@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class RouteRepositoryTests {

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

    @Test
    fun findExistingSingleRoute() {

        val route1 = Route(1, 1234, "Route 1", "hike", changedAt = now, polyline = polyline)
        every { routeRepository.findById(1) } returns Optional.of(route1)

        val route: Route = routeRepository.findById(1).orElseThrow()
        assertThat(route.name).isEqualTo("Route 1")
        assertThat(route.polyline).isEqualTo("smdeH_mwbBsAiKkbEmfK")

    }

    @Test
    fun findNonExistingSingleRoute() {

        every { routeRepository.findById(-1) } returns Optional.empty()

        val route: Route? = routeRepository.findByIdOrNull(-1)
        assertThat(route).isNull()

    }

    @Test
    fun findAllByOrderByChangedAtDesc() {

        val route1 = Route(1, 1234, "Route 1", "hike", changedAt = now, polyline = polyline)
        val route2 = Route(2, 1234, "Route 2", "mtb", changedAt = now, polyline = polyline)
        every { routeRepository.findAllByOrderByChangedAtDesc() } returns listOf(route1, route2)

        val routes: List<Route> = routeRepository.findAllByOrderByChangedAtDesc().toList()
        assertThat(routes.size).isEqualTo(2)
        assertThat(routes.first().name).isEqualTo("Route 1")

    }

    @Test
    fun findAllBySportOrderByChangedAtDesc() {

        val route1 = Route(1, 1234, "Route 1", "hike", changedAt = now, polyline = polyline)
        val route2 = Route(2, 1234, "Route 2", "hike", changedAt = now, polyline = polyline)
        every { routeRepository.findAllBySportOrderByChangedAtDesc("hike") } returns listOf(route1, route2)

        val routes: List<Route> = routeRepository.findAllBySportOrderByChangedAtDesc("hike").toList()
        assertThat(routes.size).isEqualTo(2)
        assertThat(routes.first().name).isEqualTo("Route 1")
        assertThat(routes.last().name).isEqualTo("Route 2")

    }

    @Test
    fun findAllBySportOrderByChangedAtDescNonExistingSport() {

        every { routeRepository.findAllBySportOrderByChangedAtDesc("unknown") } returns listOf()

        val routes: List<Route> = routeRepository.findAllBySportOrderByChangedAtDesc("unknown").toList()
        assertThat(routes.size).isEqualTo(0)

    }

    @Test
    fun findSportsWithCount() {

        every { routeRepository.findSportsWithCount() } returns listOf(
            Summary("hike", 20),
            Summary("jogging", 12)
        )

        val result: List<Summary> = routeRepository.findSportsWithCount().toList()
        assertThat(result.size).isEqualTo(2)
        assertThat(result.get(0)).isEqualTo(Summary(name = "hike", count = 20L))
        assertThat(result.get(1)).isEqualTo(Summary(name = "jogging", count = 12))

    }

    @Test
    fun findTotalRouteCountExisting() {

        every {routeRepository.findTotalRouteCount(123456) } returns 375

        val totalCount: Long = routeRepository.findTotalRouteCount(123456)
        assertThat(totalCount).isEqualTo(375)

    }

    @Test
    fun findTotalRouteCountNonExisting() {

        every {routeRepository.findTotalRouteCount(-1) } returns 0

        val totalCount: Long = routeRepository.findTotalRouteCount(-1)
        assertThat(totalCount).isEqualTo(0)

    }

}
