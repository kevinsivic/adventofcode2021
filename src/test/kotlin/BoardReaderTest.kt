import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test

class BoardReaderTest() {
    private lateinit var secondBoard: BingoBoard
    private lateinit var thirdBoard: BingoBoard
    private lateinit var firstBoard: BingoBoard
    private lateinit var boardReader: BoardReader

    @Before
    fun setUp() {
        val input = """7,4,9,5,11,17,23,2,0,14,21,24,10,16,13,6,15,25,12,22,18,20,8,19,3,26,1

        22 13 17 11  0
         8  2 23  4 24
        21  9 14 16  7
         6 10  3 18  5
         1 12 20 15 19
        
         3 15  0  2 22
         9 18 13 17  5
        19  8  7 25 23
        20 11 10 24  4
        14 21 16 12  6
        
        14 21 17 24  4
        10 16 15  9 19
        18  8 23 26 20
        22 11 13  6  5
         2  0 12  3  7"""

        val stream = input.byteInputStream()
        boardReader = BoardReader().readStream(stream)

        val firstBoard = """
        22 13 17 11  0
         8  2 23  4 24
        21  9 14 16  7
         6 10  3 18  5
         1 12 20 15 19
        """.trimIndent()
        this.firstBoard = BingoBoard(firstBoard)

        val secondBoard = """
         3 15  0  2 22
         9 18 13 17  5
        19  8  7 25 23
        20 11 10 24  4
        14 21 16 12  6
        """.trimIndent()
        this.secondBoard = BingoBoard(secondBoard)

        val thirdBoard = """
        14 21 17 24  4
        10 16 15  9 19
        18  8 23 26 20
        22 11 13  6  5
         2  0 12  3  7
        """.trimIndent()
        this.thirdBoard = BingoBoard(thirdBoard)
    }

    @Test
    fun canReadDraws() {
        assertEquals(7, boardReader.getNextDraw())
    }

    @Test
    fun canReadBoard() {
        assertEquals(firstBoard, boardReader.getBoards()[0])
    }

    @Test
    fun canProcessDraws() {
        assertEquals(thirdBoard, boardReader.getWinningBoard())
    }

    @Test
    fun boardReaderCanCalculateWinnersScore() {
        boardReader.getWinningBoard()
        assertEquals(4512, boardReader.getWinningScore())
    }

    @Test
    fun boardReaderCanGetLosingBoard() {
        assertEquals(secondBoard, boardReader.getLosingBoard())
    }

    @Test
    fun boardReaderCanCalculateLosersScore() {
        boardReader.getLosingBoard()
        assertEquals(1924, boardReader.getLosingScore())
    }
}