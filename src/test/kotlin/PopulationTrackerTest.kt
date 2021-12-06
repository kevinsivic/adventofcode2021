import org.junit.Assert.assertEquals
import org.junit.Test

class PopulationTrackerTest {
    @Test
    fun lanternFishPopulationIsSteadyWhenNoReproductionIsHappening() {
        val populationTracker = PopulationTracker()
        populationTracker.addFish(3)
        populationTracker.addFish(4)
        populationTracker.nextDay()
        assertEquals(2, populationTracker.getCurrentPopulation())
    }

    @Test
    fun lanternFishPopulationIncreasesWhenFishIsReadyToReproduce() {
        val populationTracker = PopulationTracker()
        populationTracker.addFish(3)
        populationTracker.addFish(0)
        populationTracker.nextDay()
        assertEquals(3, populationTracker.getCurrentPopulation())
    }

    @Test
    fun trackerCanSimulateASpecifiedNumberOfDays() {
        val populationTracker = PopulationTracker()
        populationTracker.addFish(3)
        populationTracker.addFish(4)
        populationTracker.addFish(3)
        populationTracker.addFish(1)
        populationTracker.addFish(2)
        populationTracker.simulate(18)
        assertEquals(26, populationTracker.getCurrentPopulation())
    }
}