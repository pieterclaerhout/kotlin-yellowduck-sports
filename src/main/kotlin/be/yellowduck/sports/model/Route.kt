package be.yellowduck.sports.model

import be.yellowduck.sports.utils.PolylineUtils
import be.yellowduck.sports.utils.attribute
import be.yellowduck.sports.utils.document
import be.yellowduck.sports.utils.element
import com.fasterxml.jackson.annotation.JsonIgnore
import com.fasterxml.jackson.annotation.JsonProperty
import com.sun.xml.txw2.output.IndentingXMLStreamWriter
import kotlinx.serialization.SerialName
import kotlinx.serialization.Transient
import java.io.ByteArrayOutputStream
import java.time.LocalDateTime
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.Id
import javax.xml.stream.XMLOutputFactory

@Entity(name = "routes")
data class Route(
    @Id @GeneratedValue var id: Long? = null,
    @SerialName("token_id") @JsonProperty("token_id") val tokenId: Long,
    val name: String,
    val sport: String,
    @Transient @JsonIgnore val polyline: String = "",
    @SerialName("changed_at") @JsonProperty("changed_at") val changedAt: LocalDateTime = LocalDateTime.now()
) {

    fun toGPX(): String {

        val coordinates = PolylineUtils.decode(polyline)

        var stream = ByteArrayOutputStream()
        val writer = IndentingXMLStreamWriter(XMLOutputFactory.newFactory().createXMLStreamWriter(stream, "UTF-8"))

        writer.setIndentStep("  ")
        writer.document {
            element("gpx") {
                attribute("version", "1.1")
                attribute("creator" , "sports.yellowduck.be")
                attribute("xmlns", "http://www.topografix.com/GPX/1/1")
                element("metadata") {
                    element("name", name)
                }
                element("trk") {
                    element("name", name)
                    element("trkseg") {
                        coordinates.forEach {
                            element("trkpt") {
                                attribute("lat", it.latitude.toString())
                                attribute("lon", it.longitude.toString())
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
