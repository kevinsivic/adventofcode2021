class Command(val direction: Enum<CommandDirection>, val distance: Int) {
    override fun equals(other: Any?): Boolean {
        if (other is Command &&
            this.direction == other.direction &&
            this.distance == other.distance)
            return true
        return false
    }

    override fun hashCode(): Int {
        return javaClass.hashCode()
    }
}

enum class CommandDirection {
    FORWARD, DOWN, UP
}
