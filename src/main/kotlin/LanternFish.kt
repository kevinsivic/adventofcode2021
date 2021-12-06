class LanternFish {
    constructor()

    constructor(daysToReproduce: Int) {
        this.daysToReproduce = daysToReproduce
    }

    private var daysToReproduce = 8

    fun getDaysUntilReproduction(): Int {
        return daysToReproduce
    }

    fun isReadyToReproduce(): Boolean {
        return daysToReproduce == 0
    }

    fun nextDay() {
        if (daysToReproduce == 0) {
            daysToReproduce = 6
            return
        }

        daysToReproduce--
    }

}
