package be.yellowduck.sports.gpx

import com.sun.xml.txw2.output.IndentingXMLStreamWriter
import java.io.ByteArrayOutputStream
import javax.xml.stream.XMLOutputFactory

data class Document (
    val path: String = "",
    val name: String = "",
    val tracks: MutableList<Track> = mutableListOf()
) {

    fun toGPXString(): String {

        var stream = ByteArrayOutputStream()
        val writer = IndentingXMLStreamWriter(XMLOutputFactory.newFactory().createXMLStreamWriter(stream, "UTF-8"))

        writer.setIndentStep("  ")
        writer.document {
            element("gpx") {
                attribute("version", "1.1")
                attribute("creator" , "sports.yellowduck.be")
                attribute("xmlns", "http://www.topografix.com/GPX/1/1")
                if (!name.isNullOrBlank()) {
                    element("metadata") {
                        element("name", name)
                    }
                }
                tracks.forEach { track ->
                    element("trk") {
                        if (!track.name.isNullOrBlank()) {
                            element("name", track.name)
                        }
                        track.segments.forEach { segment ->
                            element("trkseg") {
                                segment.points.forEach { point ->
                                    element("trkpt") {
                                        attribute("lat", point.lat.toString())
                                        attribute("lon", point.lon.toString())
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }

        writer.flush()

        return stream.toString("UTF-8")

    }

}