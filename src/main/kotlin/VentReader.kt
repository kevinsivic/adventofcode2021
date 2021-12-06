import java.io.BufferedReader
import java.io.InputStream

class VentReader(stream: InputStream, mapSize: Int) {
    private var map: Array<Array<Int>> = Array(mapSize) { Array(mapSize) { 0 } }
    private var reader: BufferedReader

    init {
        reader = stream.bufferedReader()
    }

    fun getNextLine(): Line {
        val nextLine = reader.readLine()
        return getLineFrom(nextLine)
    }

    private fun getLineFrom(nextLine: String): Line {
        val (startPoint, endPoint) = nextLine.split("->").map { point -> point.trim() }
        val (startX, startY) = startPoint.split(",").map { coordinate -> coordinate.toInt() }
        val (endX, endY) = endPoint.split(",").map { coordinate -> coordinate.toInt() }
        return Line(Point(startX, startY), Point(endX, endY))
    }

    fun getMapOfVents(): Array<Array<Int>> {
        reader.forEachLine { stringLine ->
            val line = getLineFrom(stringLine)
            map = line.updateMap(map)
        }
        return map
    }

    fun getCountOfDangerZones(): Int {
        var dangerZones = 0
        map.forEach {row ->
            dangerZones += row.count {point ->
                point > 1
            }
        }
        return dangerZones
    }

}
