import java.io.StringReader

class BingoBoard(board: String) {
    private var boardArray: Array<Array<BingoBoardEntry>> = Array(5) {Array(5) {BingoBoardEntry(-1)} }
    init {
        val reader = StringReader(board)
        var x = 0
        reader.forEachLine { line ->
            var y = 0
            val entries = line.trim().split("\\s+".toRegex()).map { entry -> entry.trim()}
            entries.forEach { entry ->
                boardArray[x][y] = BingoBoardEntry(entry.toInt())
                y++
            }
            x++
        }
    }

    fun get(x: Int, y: Int): Int {
        return boardArray[x][y].toInt()
    }

    fun isMarked(x: Int, y: Int): Boolean {
        return boardArray[x][y].isMarked()
    }

    fun mark(value: Int) {
        boardArray.forEach {
            it.forEach {
                if (it.equals(value))
                    it.setMarkedFlag(true)
            }
        }
    }

    fun isSolved(): Boolean {
        if (checkRows()) return true
        if (checkColumns()) return true

        return false
    }

    private fun checkRows(): Boolean {
        boardArray.forEach {
            if (checkEntries(it.toList())) return true
        }
        return false
    }

    private fun checkColumns(): Boolean {
        for (columnIndex in boardArray.indices) {
            val column = boardArray.map { entry -> entry[columnIndex] }
            if (checkEntries(column)) return true
        }
        return false
    }

    private fun checkEntries(column: List<BingoBoardEntry>): Boolean {
        if (5 == column.count { entry -> entry.isMarked() })
            return true
        return false
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as BingoBoard

        if (!boardArray.contentDeepEquals(other.boardArray)) return false

        return true
    }

    override fun hashCode(): Int {
        return boardArray.contentDeepHashCode()
    }

    override fun toString(): String {
        var string = "\n"
        boardArray.forEach {row ->
            row.forEach { entry ->
                string += entry.value.toString() + " "
            }
            string += "\n"
        }
        return string
    }

    fun getSum(): Int {
        var sum = 0
        boardArray.forEach { row ->
            row.forEach { entry ->
                if (!entry.isMarked())
                    sum += entry.value
            }
        }
        return sum
    }

}
