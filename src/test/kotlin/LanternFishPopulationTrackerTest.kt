import org.junit.Assert.assertEquals
import org.junit.Test

class LanternFishPopulationTrackerTest {
    @Test
    fun lanternFishPopulationIsSteadyWhenNoReproductionIsHappening() {
        val populationTracker = PopulationTracker()
        populationTracker.add(LanternFish(3))
        populationTracker.add(LanternFish(4))
        populationTracker.nextDay()
        assertEquals(2, populationTracker.getCurrentPopulation())
    }

    @Test
    fun lanternFishPopulationIncreasesWhenFishIsReadyToReproduce() {
        val populationTracker = PopulationTracker()
        populationTracker.add(LanternFish(3))
        populationTracker.add(LanternFish(0))
        populationTracker.nextDay()
        assertEquals(3, populationTracker.getCurrentPopulation())
    }

    @Test
    fun trackerCanSimulateASpecifiedNumberOfDays() {
        val populationTracker = PopulationTracker()
        populationTracker.add(LanternFish(3))
        populationTracker.add(LanternFish(4))
        populationTracker.add(LanternFish(3))
        populationTracker.add(LanternFish(1))
        populationTracker.add(LanternFish(2))
        populationTracker.simulate(18)
        assertEquals(26, populationTracker.getCurrentPopulation())
    }
}