package be.yellowduck.sports.model

import be.yellowduck.sports.gpx.Polyline
import com.fasterxml.jackson.annotation.JsonIgnore
import com.fasterxml.jackson.annotation.JsonProperty
import kotlinx.serialization.SerialName
import kotlinx.serialization.Transient
import java.time.LocalDateTime
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.Id

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
        return Polyline.decodeToGPX(polyline, name).toGPXString()
    }

}
