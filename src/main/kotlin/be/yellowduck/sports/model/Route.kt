package be.yellowduck.sports.model

import be.yellowduck.gpx.Distance
import be.yellowduck.gpx.Polyline
import com.fasterxml.jackson.annotation.JsonIgnore
import com.fasterxml.jackson.annotation.JsonProperty
import kotlinx.serialization.SerialName
import kotlinx.serialization.Transient
import org.hibernate.annotations.Type
import java.time.LocalDateTime
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.Id

@Entity(name = "routes")
//@EntityListeners(RouteAuditTrailListener::class)
data class Route(
    @Id
    @GeneratedValue
    var id: Long? = null,

    @SerialName("token_id")
    @JsonProperty("token_id")
    val tokenId: Long,

//    @Type(type = "be.yellowduck.sports.model.PhoneNumberType")
    val name: String,

    val sport: String,

    @Type(type = "be.yellowduck.sports.model.DistanceType")
    val distance: Distance = Distance(0.0),

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

//    @PostLoad
//    fun onPostLoad() {
////        println("post-load: ${id} ${name}")
//        distanceObject = Distance(distance)
//    }

}
