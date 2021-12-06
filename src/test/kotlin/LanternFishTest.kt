import org.junit.Assert.assertEquals
import org.junit.Test

class LanternFishTest {
    @Test
    fun newLanternFishDefaultsTo8DaysToReproduction() {
        val fish = LanternFish()
        assertEquals(8, fish.getDaysUntilReproduction())
    }

    @Test
    fun daysToReproductionDecreasesEachDay() {
        val fish = LanternFish()
        fish.nextDay()
        assertEquals(7, fish.getDaysUntilReproduction())
    }

    @Test
    fun canCreateFishWithSpecificDaysToReproduce() {
        val fish = LanternFish(5)
        assertEquals(5, fish.getDaysUntilReproduction())
    }

    @Test
    fun daysToReproductionRollsOverAfter0() {
        val fish = LanternFish(0)
        fish.nextDay()
        assertEquals(6, fish.getDaysUntilReproduction())
    }
}