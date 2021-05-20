package be.yellowduck.sports.model

import be.yellowduck.gpx.Distance
import be.yellowduck.gpx.Polyline
import com.fasterxml.jackson.annotation.JsonIgnore
import com.fasterxml.jackson.annotation.JsonProperty
import kotlinx.serialization.SerialName
import kotlinx.serialization.Transient
import java.time.LocalDateTime
import javax.persistence.*

@Entity(name = "routes")
//@EntityListeners(RouteAuditTrailListener::class)
data class Route(
    @Id
    @GeneratedValue
    var id: Long? = null,

    @SerialName("token_id")
    @JsonProperty("token_id")
    val tokenId: Long,

    val name: String,

    val sport: String,

//    @Type(type = "be.yellowduck.sports.model.DistanceType")
    val distance: Double = 0.0,

//    @Transient
//    @JsonIgnore
//    var distanceObject: Distance = Distance(0.0),

    @Transient
    @JsonIgnore
    val polyline: String = "",

    @SerialName("changed_at")
    @JsonProperty("changed_at")
    val changedAt: LocalDateTime = LocalDateTime.now()
) {

    fun toGPX(): String {
        return Polyline.decodeToGPX(polyline, name).toString()
    }

    val formattedKilometers: String
        get() {
            return Distance(distance).formattedKilometers
        }

//    @PostLoad
//    fun onPostLoad() {
////        println("post-load: ${id} ${name}")
//        distanceObject = Distance(distance)
//    }

}
