package be.yellowduck.sports.gpx

import org.w3c.dom.Node
import org.xml.sax.InputSource
import java.io.FileInputStream
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import javax.xml.parsers.DocumentBuilderFactory

class Parser {

    fun parse(path: String): Document {

        val fis = FileInputStream(path)

        var gpx = Document(path)

        val dbFactory = DocumentBuilderFactory.newInstance()
        val dBuilder = dbFactory.newDocumentBuilder()
        val xmlInput = InputSource(fis)
        val doc = dBuilder.parse(xmlInput)

        for (item in doc.getElementsByTagName("trk").items()) {
            val track = parseTrack(item)
            gpx.tracks.add(track)
        }

        return gpx

    }

    private fun parseTrack(node: Node): Track {
        var trackName = node.firstChildByName("name")?.textContent.orEmpty()
        var track = Track(trackName)
        for (child in node.childrenByName("trkseg")) {
            val segment = parseSegment(child)
            track.segments.add(segment)
        }
        return track
    }

    private fun parseSegment(node: Node): Segment {
        var segment = Segment()
        for (child in node.childrenByName("trkpt")) {
            val trackPoint = parseTrackPoint(child)
            segment.points.add(trackPoint)
        }
        return segment
    }

    private fun parseTrackPoint(node: Node): TrackPoint {

        var formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss'Z'")
        var parsedDate = LocalDateTime.parse(node.firstChildByName("time")?.textContent ?: "", formatter)

        return TrackPoint(
            lat = node.doubleAttribute("lat"),
            lon = node.doubleAttribute("lon"),
            ele = node.firstChildByName("ele")?.textContent?.toDoubleOrNull() ?: 0.0,
            time = parsedDate,
        )

    }

}
