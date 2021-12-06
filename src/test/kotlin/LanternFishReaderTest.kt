import org.junit.Assert.assertEquals
import org.junit.Test
import java.io.InputStreamReader

class LanternFishReaderTest {

    @Test
    fun canCreatePopulationTrackerBasedOnInput() {
        val input = "3,4,3,1,2"
        val stream = input.byteInputStream()

        val populationReader = PopulationReader(stream)

        val populationTracker = populationReader.getTracker()
        assertEquals(5, populationTracker.getCurrentPopulation())
    }
}