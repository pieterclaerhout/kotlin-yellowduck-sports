package be.yellowduck.sports.gpx

import kotlinx.serialization.Contextual
import kotlinx.serialization.Serializable
import java.time.LocalDateTime

@Serializable
data class TrackPoint (
    var lon: Double = 0.0,
    var lat: Double = 0.0,
    var ele: Double = 0.0,
    @Contextual var time: LocalDateTime? = null,
)
