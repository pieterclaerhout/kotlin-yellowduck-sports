package be.yellowduck.sports.utils

import com.fasterxml.jackson.annotation.JsonProperty
import kotlinx.serialization.*

@Serializable
data class Coordinate(
    @SerialName("lon") @JsonProperty("lon") val longitude: Double,
    @SerialName("lat") @JsonProperty("lat") val latitude: Double
)
