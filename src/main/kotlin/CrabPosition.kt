import java.io.InputStream
import java.util.*

class CrabPosition {
    private lateinit var positions: List<Int>
    fun read(stream: InputStream) {
        positions = stream.reader().readText().split(",").map { position -> position.toInt()}
    }

    fun getPositions(): String {
        return positions.joinToString(",")
    }

    fun getCheapestPosition(): Int {
        val costs = mutableMapOf<Int,Int>()
        for (position in 0..Collections.max(positions)) {
            costs[getCostToMoveTo(position)] = position
        }
        val minCost = Collections.min(costs.keys)
        return costs[minCost]!!
    }

    fun getCostToMoveTo(targetPosition: Int): Int {
        var totalCost = 0
        positions.forEach { crabPosition ->
            val distance =  Math.abs(crabPosition - targetPosition)
            for (i in 0..distance) {
                totalCost += i
            }
        }
        return totalCost
    }
}
