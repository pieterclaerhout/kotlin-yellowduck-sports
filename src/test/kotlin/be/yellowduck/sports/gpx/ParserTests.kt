package be.yellowduck.sports.gpx

import be.yellowduck.sports.gpx.Parser
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import java.io.File
import java.time.ZoneOffset

class ParserTests {

    @Test
    fun testParser() {

        val path = "src/test/resources/Gravelgrinders_Gent_4.gpx"

        val parser = Parser()

        val gpx = parser.parse(path)
        assertThat(gpx.tracks).isNotNull()
        assertThat(gpx.tracks.size).isEqualTo(1)

        val track = gpx.tracks.first()
        assertThat(track.name).isEqualTo("Gravelgrinders Gent 4")
        assertThat(track.segments.size).isEqualTo(1)

        val segment = track.segments.first()
        assertThat(segment.points.size).isEqualTo(15478)

        val point = segment.points.first()
        assertThat(point.lat).isEqualTo(51.003349)
        assertThat(point.lon).isEqualTo(3.804193)
        assertThat(point.ele).isEqualTo(2.6)
        assertThat(point.time?.toEpochSecond(ZoneOffset.UTC)).isEqualTo(1600587025)

    }

    @Test
    fun testAllFiles() {

        File("src/test/resources").walk().filter { it.name.endsWith(".gpx") }.forEach {
            println(it.absolutePath)
        }

    }

}
