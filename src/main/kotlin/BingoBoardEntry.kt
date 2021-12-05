import kotlin.reflect.typeOf

class BingoBoardEntry (val value: Int, var marked: Boolean = false) {
    fun toInt(): Int {
        return value
    }

    fun setMarkedFlag(marked: Boolean) {
        this.marked = marked
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other is Int && value == other) return true

        if (javaClass != other?.javaClass) return false

        other as BingoBoardEntry

        if (value != other.value) return false

        return true
    }

    override fun hashCode(): Int {
        return value
    }

    fun isMarked(): Boolean {
        return marked
    }

}
