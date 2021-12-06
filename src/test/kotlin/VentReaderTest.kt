import org.junit.Assert.assertArrayEquals
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test

class VentReaderTest() {
    private lateinit var map: Array<Array<Int>>
    private lateinit var ventReader: VentReader

    @Before
    fun setUp() {
        val input = """
            0,9 -> 5,9
            8,0 -> 0,8
            9,4 -> 3,4
            2,2 -> 2,1
            7,0 -> 7,4
            6,4 -> 2,0
            0,9 -> 2,9
            3,4 -> 1,4
            0,0 -> 8,8
            5,5 -> 8,2
        """.trimIndent()

        val stream = input.byteInputStream()
        ventReader = VentReader(stream, 10)

        map = arrayOf(
            arrayOf(1,0,1,0,0,0,0,1,1,0),
            arrayOf(0,1,1,1,0,0,0,2,0,0),
            arrayOf(0,0,2,0,1,0,1,1,1,0),
            arrayOf(0,0,0,1,0,2,0,2,0,0),
            arrayOf(0,1,1,2,3,1,3,2,1,1),
            arrayOf(0,0,0,1,0,2,0,0,0,0),
            arrayOf(0,0,1,0,0,0,1,0,0,0),
            arrayOf(0,1,0,0,0,0,0,1,0,0),
            arrayOf(1,0,0,0,0,0,0,0,1,0),
            arrayOf(2,2,2,1,1,1,0,0,0,0),
        )
    }

    @Test
    fun canGetStraightLine() {
        val startingPoint = Point(0, 9)
        val endingPoint = Point(5,9)
        val line = Line(startingPoint, endingPoint)

        assertEquals(line, ventReader.getNextLine())
    }

    @Test
    fun canBuildMapOfVents() {
        assertArrayEquals(map, ventReader.getMapOfVents())
    }

    @Test
    fun dangerZonesAreWhereMultipleLinesOverlap() {
        ventReader.getMapOfVents()
        assertEquals(12, ventReader.getCountOfDangerZones())
    }
}