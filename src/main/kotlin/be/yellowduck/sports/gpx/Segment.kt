package be.yellowduck.sports.gpx;

data class Segment(
    val points: MutableList<TrackPoint> = mutableListOf()
) {

    fun distance() : Distance {
        var distance: Double = 0.0
        points.forEachIndexed { index, point ->
            if (index > 0) {
                distance += points[index-1].distanceTo(point)
            }
        }
        return  Distance(distance)
    }

    fun toPolyline(): String {
        return Polyline.encode(points);
    }

}
