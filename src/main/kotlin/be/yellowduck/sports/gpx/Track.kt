package be.yellowduck.sports.gpx

import be.yellowduck.sports.gpx.Polyline

data class Track(
    val name: String = "",
    val segments: MutableList<Segment> = mutableListOf()
) {

    fun toPolyline(): String {
        val coords: MutableList<TrackPoint> = mutableListOf()
        segments.forEach {
            coords.addAll(it.points)
        }
        return Polyline.encode(coords);
    }

}