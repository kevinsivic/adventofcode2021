class PopulationTracker() {
    private var fishBuckets: MutableList<Long> = mutableListOf(0,0,0,0,0,0,0,0,0)

    fun getCurrentPopulation(): Long {
        var countOfFish: Long = 0
        fishBuckets.forEach {
            countOfFish += it
        }
        return countOfFish
    }

    fun addFish(daysUntilReproduction: Int) {
        fishBuckets[daysUntilReproduction]++
    }

    fun nextDay() {
        val numberToAdd = fishBuckets[0]
        for (i in 1 until fishBuckets.size)
            fishBuckets[i-1] = fishBuckets[i]
        fishBuckets[6] += numberToAdd
        fishBuckets[8] = 0

        bulkAddNewFish(numberToAdd)

    }

    private fun bulkAddNewFish(numberToAdd: Long) {
        fishBuckets[8] += numberToAdd
    }

    fun simulate(days: Int) {
        for (i in 1..days) {
            this.nextDay()
            if (i % 10 == 0)
                println("The population is " + this.getCurrentPopulation() + " after " + i + " days.")
        }
    }

}
