import org.junit.Assert.*
import org.junit.Before
import org.junit.Test

class BingoBoardTest() {

    private lateinit var bingoBoard: BingoBoard

    @Test
    fun canCreateBingoBoard() {
        assertEquals(14, bingoBoard.get(2,2))
    }

    @Test
    fun canMarkBoardEntry() {
        bingoBoard.mark(14)
        assertTrue(bingoBoard.isMarked(2,2))
    }

    @Test
    fun boardCanBeSolvedHorizontally() {
        bingoBoard.mark(14)
        bingoBoard.mark(16)
        bingoBoard.mark(7)
        bingoBoard.mark(9)
        bingoBoard.mark(21)

        assertTrue(bingoBoard.isSolved())
    }

    @Test
    fun boardCanBeSolvedVertically() {
        bingoBoard.mark(13)
        bingoBoard.mark(2)
        bingoBoard.mark(9)
        bingoBoard.mark(10)
        bingoBoard.mark(12)

        assertTrue(bingoBoard.isSolved())
    }

    @Test
    fun boardIsUnsolvedWhenOriginIsMarked() {
        bingoBoard.mark(22)

        assertFalse(bingoBoard.isSolved())
    }

    @Test
    fun boardCanCalculateSumOfUnmarkedValues() {
        assertEquals(300, bingoBoard.getSum())
        bingoBoard.mark(14)
        assertEquals(286, bingoBoard.getSum())

    }

    @Before
    fun setUp() {
        val board = """
                22 13 17 11  0
                 8  2 23  4 24
                21  9 14 16  7
                 6 10  3 18  5
                 1 12 20 15 19
            """.trimIndent()
        bingoBoard = BingoBoard(board)
    }
}