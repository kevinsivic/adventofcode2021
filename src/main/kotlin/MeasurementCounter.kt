import java.util.ArrayList

class MeasurementCounter {
    fun isIncreasing(previous: Int, current: Int): Boolean {
        return current > previous
    }

    fun countIncreasing(measurements: ArrayList<Int>, windowSize: Int): Int {
        var count = 0
        for (i in 1..(measurements.size - windowSize)) {
            val previousMeasurement = getWindowTotal(i - 1, windowSize, measurements)
            val currentMeasurement = getWindowTotal(i, windowSize, measurements)

            if (isIncreasing(previousMeasurement, currentMeasurement))
                count++
        }

        return count
    }

    private fun getWindowTotal(
        windowStart: Int,
        windowSize: Int,
        measurements: ArrayList<Int>
    ): Int {
        var currentMeasurement = 0
        for (j in 0 until windowSize) {
            currentMeasurement += measurements[windowStart + j]
        }
        return currentMeasurement
    }
}
