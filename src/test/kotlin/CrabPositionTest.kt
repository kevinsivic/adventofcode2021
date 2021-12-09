import org.junit.Assert.assertEquals
import org.junit.Test

class CrabPositionTest {
    @Test
    fun canReadSetOfCrabPositions() {
        val input = "16,1,2,0,4,2,7,1,2,14"
        val stream = input.byteInputStream()

        val crabPosition = CrabPosition()
        crabPosition.read(stream)
        assertEquals(input, crabPosition.getPositions())
    }

    @Test
    fun canFindCheapestPositionToMoveTo() {
        val input = "16,1,2,0,4,2,7,1,2,14"
        val stream = input.byteInputStream()

        val crabPosition = CrabPosition()
        crabPosition.read(stream)
        assertEquals(5, crabPosition.getCheapestPosition())
    }

    @Test
    fun getCostToMoveToPosition() {
        val input = "16,1,2,0,4,2,7,1,2,14"
        val stream = input.byteInputStream()

        val crabPosition = CrabPosition()
        crabPosition.read(stream)
        assertEquals(168, crabPosition.getCostToMoveTo(5))
    }
}