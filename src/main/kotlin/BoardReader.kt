import java.io.BufferedReader
import java.io.InputStream
import java.io.InputStreamReader
import java.util.*

class BoardReader {
    private var losingDraw: Int = -1
    private lateinit var losingBoard: BingoBoard
    private var winningDraw: Int = -1
    private lateinit var winningBoard: BingoBoard
    private var boards: MutableList<BingoBoard> = mutableListOf()
    private lateinit var draws: LinkedList<Int>
    fun readStream(stream: InputStream): BoardReader {
        val reader = BufferedReader(InputStreamReader(stream))
        val firstLine = reader.readLine()
        draws = LinkedList<Int>(firstLine.split(",").map {draw -> draw.toInt()})

        while (reader.readLine() != null) {
            var line = ""
            for (lineNumber in 1..5)
                line += reader.readLine() + "\n"
            boards.add(BingoBoard(line))
        }

        return this
    }

    fun getNextDraw(): Int {
        return draws.pop()
    }

    fun getBoards(): List<BingoBoard> {
        return boards
    }

    fun getWinningBoard(): BingoBoard? {
        draws.forEach { draw ->
            boards.forEach { board ->
                board.mark(draw)
                if (board.isSolved()) {
                    winningBoard = board
                    winningDraw = draw
                    return board
                }
            }
        }
        return null
    }

    fun getWinningScore(): Int {
        return winningBoard.getSum() * winningDraw
    }

    fun getLosingBoard(): BingoBoard? {
        draws.forEach { draw ->
            boards.forEach { board ->
                board.mark(draw)
                if (boards.none { board -> !board.isSolved() }) {
                    losingBoard = board
                    losingDraw = draw
                    return losingBoard
                }
            }
        }
        return null
    }

    fun getLosingScore(): Int {
        return losingBoard.getSum() * losingDraw
    }

}
