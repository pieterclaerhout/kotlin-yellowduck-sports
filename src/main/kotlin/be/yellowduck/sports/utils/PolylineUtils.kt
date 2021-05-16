package be.yellowduck.sports.utils

class PolylineUtils {

    companion object {

        fun encode(coords: List<Coordinate>): String {
            val result: MutableList<String> = mutableListOf()

            var prevLat = 0
            var prevLong = 0

            for ((long, lat) in coords) {
                val iLat = (lat * 1e5).toInt()
                val iLong = (long * 1e5).toInt()

                val deltaLat = encodeValue(iLat - prevLat)
                val deltaLong = encodeValue(iLong - prevLong)

                prevLat = iLat
                prevLong = iLong

                result.add(deltaLat)
                result.add(deltaLong)
            }

            return result.joinToString("")
        }

        private fun encodeValue(value: Int): String {
            var actualValue = if (value < 0) (value shl 1).inv() else (value shl 1)
            val chunks: List<Int> = splitIntoChunks(actualValue)
            return chunks.map { (it + 63).toChar() }.joinToString("")
        }

        private fun splitIntoChunks(toEncode: Int): List<Int> {
            val chunks = mutableListOf<Int>()
            var value = toEncode
            while (value >= 32) {
                chunks.add((value and 31) or (0x20))
                value = value shr 5
            }
            chunks.add(value)
            return chunks
        }

        fun decode(polyline: String): List<Coordinate> {

            val coordinateChunks: MutableList<MutableList<Int>> = mutableListOf()
            coordinateChunks.add(mutableListOf())

            for (char in polyline.toCharArray()) {

                var value = char.toInt() - 63

                val isLastOfChunk = (value and 0x20) == 0
                value = value and (0x1F)

                coordinateChunks.last().add(value)

                if (isLastOfChunk) {
                    coordinateChunks.add(mutableListOf())
                }

            }

            coordinateChunks.removeAt(coordinateChunks.lastIndex)

            var coordinates: MutableList<Double> = mutableListOf()

            for (coordinateChunk in coordinateChunks) {
                var coordinate = coordinateChunk.mapIndexed { i, chunk -> chunk shl (i * 5) }.reduce { i, j -> i or j }

                if (coordinate and 0x1 > 0) {
                    coordinate = (coordinate).inv()
                }

                coordinate = coordinate shr 1
                coordinates.add((coordinate).toDouble() / 100000.0)
            }

            val points: MutableList<Coordinate> = mutableListOf()
            var previousX = 0.0
            var previousY = 0.0

            for (i in 0..coordinates.size - 1 step 2) {

                if (coordinates[i] == 0.0 && coordinates[i + 1] == 0.0) {
                    continue
                }

                previousX += coordinates[i + 1]
                previousY += coordinates[i]

                points.add(Coordinate(round(previousX, 5), round(previousY, 5)))
            }

            return points

        }

        private fun round(value: Double, precision: Int) =
            (value * Math.pow(10.0, precision.toDouble())).toInt().toDouble() / Math.pow(10.0, precision.toDouble())

        fun simplify(points: List<Coordinate>, epsilon: Double): List<Coordinate> {

            var dmax = 0.0
            var index = 0
            var end = points.size

            for (i in 1..(end - 2)) {
                var d = perpendicularDistance(points[i], points[0], points[end - 1])
                if (d > dmax) {
                    index = i
                    dmax = d
                }
            }

            return if (dmax > epsilon) {
                val recResults1: List<Coordinate> = simplify(points.subList(0, index + 1), epsilon)
                val recResults2: List<Coordinate> = simplify(points.subList(index, end), epsilon)
                listOf(recResults1.subList(0, recResults1.lastIndex), recResults2).flatMap { it.toList() }
            } else {
                listOf(points[0], points[end - 1])
            }

        }

        private fun perpendicularDistance(pt: Coordinate, lineFrom: Coordinate, lineTo: Coordinate): Double =
            Math.abs((lineTo.longitude - lineFrom.longitude) * (lineFrom.latitude - pt.latitude) - (lineFrom.longitude - pt.longitude) * (lineTo.latitude - lineFrom.latitude)) /
                    Math.sqrt(
                        Math.pow(
                            lineTo.longitude - lineFrom.longitude,
                            2.0
                        ) + Math.pow(lineTo.latitude - lineFrom.latitude, 2.0)
                    )

    }

}