class PopulationTracker {
    private var countOfFish: Long = 0

    private var countOfFishInBuckets: MutableList<Long> = mutableListOf(0,0,0,0,0,0,0,0,0)

    fun getCurrentPopulation(): Long {
        return countOfFish
    }

    fun add(lanternFish: LanternFish) {
        add(lanternFish.getDaysUntilReproduction())
    }

    private fun add(daysUntilReproduction: Int) {
        countOfFishInBuckets[daysUntilReproduction]++
        countOfFish++
    }

    fun nextDay() {
        val numberToAdd = countOfFishInBuckets[0]
        for (i in 1 until countOfFishInBuckets.size)
            countOfFishInBuckets[i-1] = countOfFishInBuckets[i]
        countOfFishInBuckets[6] += numberToAdd
        countOfFishInBuckets[8] = 0

        addNewFish(numberToAdd)

    }

    private fun addNewFish(numberToAdd: Long) {
        countOfFishInBuckets[8] += numberToAdd
        countOfFish += numberToAdd
    }

    fun simulate(days: Int) {
        for (i in 1..days) {
            this.nextDay()
            if (i % 10 == 0)
                println("The population is " + this.getCurrentPopulation() + " after " + i + " days.")
        }
    }

}
