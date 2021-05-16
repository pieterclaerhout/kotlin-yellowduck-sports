package be.yellowduck.sports.gpx;

data class Segment(
    var points: MutableList<TrackPoint> = mutableListOf()
) {

    fun toPolyline(): String {
        return Polyline.encode(points);
    }

}