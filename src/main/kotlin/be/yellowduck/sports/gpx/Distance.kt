package be.yellowduck.sports.gpx

data class Distance(
    private val lengthInMeters: Double = 0.0
) {

    companion object {
        const val metersPerKilometer = 1000.0
        const val metersPerMile = 1609.0
    }

    fun meters(): Double {
        return lengthInMeters
    }

    fun kilometers(): Double {
        return lengthInMeters / metersPerKilometer
    }

    fun miles(): Double {
        return lengthInMeters / metersPerMile
    }

}