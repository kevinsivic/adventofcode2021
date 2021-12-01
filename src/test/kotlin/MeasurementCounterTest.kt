import org.junit.Assert.*
import org.junit.Test

class MeasurementCounterTest {

    private val measurementCounter: MeasurementCounter
        get() {
            return MeasurementCounter()
        }

    @Test
    fun measurementCounterCanDetermineIfMeasurementIsIncreasing(){
        assertTrue(measurementCounter.isIncreasing(100, 101))
        assertFalse(measurementCounter.isIncreasing(101, 100))
        assertFalse(measurementCounter.isIncreasing(100, 100))
    }

    @Test
    fun measurementCounterCanCountIncreasingMeasurements() {
        val increasingMeasurements = arrayListOf(100, 101, 102, 103, 104, 105, 106, 107, 108, 109, 110)
        assertEquals(
            10,
            measurementCounter.countIncreasing(increasingMeasurements, 1)
        )
    }

    @Test
    fun measurementCounterCanCountWithDecreasingSteps() {
        val decreasingMeasurements = arrayListOf(100, 99, 101, 102, 100, 103)
        assertEquals(
            3,
            measurementCounter.countIncreasing(decreasingMeasurements, 1)
        )
    }

    @Test
    fun measurementCounterCanConsiderSlidingWindow() {
        val measurements = arrayListOf(199, 200, 208, 210, 200, 207, 240, 269, 260, 263)
        val windowSize = 3

        assertEquals(5, measurementCounter.countIncreasing(measurements, windowSize))
    }

}