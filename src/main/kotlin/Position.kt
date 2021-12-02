class Position(var horizontalPosition: Int, var verticalPosition: Int) {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as Position

        if (horizontalPosition != other.horizontalPosition) return false
        if (verticalPosition != other.verticalPosition) return false

        return true
    }

    override fun hashCode(): Int {
        var result = horizontalPosition
        result = 31 * result + verticalPosition
        return result
    }
}
