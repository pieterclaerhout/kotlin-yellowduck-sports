package be.yellowduck.sports.gpx

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import java.time.LocalDateTime
import java.time.ZoneOffset

class TrackPointTests {

    val now = LocalDateTime.ofEpochSecond(1621174415, 0, ZoneOffset.UTC)

    @Test
    fun newTrackPointConstructor() {

        var point = TrackPoint(
            lon = 16.34528,
            lat = 48.1969,
            ele = 2.6,
            time = now
        )

        assertThat(point.lon).isEqualTo(16.34528)
        assertThat(point.lat).isEqualTo(48.1969)
        assertThat(point.ele).isEqualTo(2.6)
        assertThat(point.time).isEqualTo(now)

    }

    @Test
    fun newTrackPointSetters() {

        var point = TrackPoint()
        point.lon = 16.34528
        point.lat = 48.1969
        point.ele = 2.6
        point.time = now

        assertThat(point.lon).isEqualTo(16.34528)
        assertThat(point.lat).isEqualTo(48.1969)
        assertThat(point.ele).isEqualTo(2.6)
        assertThat(point.time).isEqualTo(now)

    }

}