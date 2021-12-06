class Line(val startingPoint: Point, val endingPoint: Point) {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as Line

        if (startingPoint != other.startingPoint) return false
        if (endingPoint != other.endingPoint) return false

        return true
    }

    override fun hashCode(): Int {
        var result = startingPoint.hashCode()
        result = 31 * result + endingPoint.hashCode()
        return result
    }

    override fun toString(): String {
        return "Line(startingPoint=$startingPoint, endingPoint=$endingPoint)"
    }

    fun updateMap(map: Array<Array<Int>>): Array<Array<Int>> {
        if (startingPoint.x == endingPoint.x) {
            val(lowY, highY) = sortPoints(startingPoint.y, endingPoint.y)
            for (i in lowY..highY)
                map[i][startingPoint.x]++
        } else if (startingPoint.y == endingPoint.y) {
            val(lowX, highX) = sortPoints(startingPoint.x, endingPoint.x)
            for (i in lowX..highX)
                map[startingPoint.y][i]++
        } else {
            var yIter = startingPoint.y
            var xIter = startingPoint.x
            while (yIter != endingPoint.y) {
                map[yIter][xIter]++
                if (xIter < endingPoint.x)
                    xIter++
                else
                    xIter--
                if (yIter < endingPoint.y)
                    yIter++
                else
                    yIter--
            }
            map[yIter][xIter]++
        }
        return map
    }

    private fun sortPoints(firstPoint: Int, secondPoint: Int) = listOf(firstPoint, secondPoint).sorted()
}
