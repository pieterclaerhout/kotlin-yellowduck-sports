package be.yellowduck.sports.gpx

data class Track(
    val name: String = "",
    val segments: MutableList<Segment> = mutableListOf()
) {

    fun distance(): Distance {
        return Distance(segments.sumOf { it.distance().meters() })
    }

    fun toPolyline(): String {
        val coords: MutableList<TrackPoint> = mutableListOf()
        segments.forEach {
            coords.addAll(it.points)
        }
        return Polyline.encode(coords);
    }

}