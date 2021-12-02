import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test

class PositionCalculatorTest {
    private var positionCalculator: PositionCalculator = PositionCalculator()
    private var commands = ArrayList<Command>()

    @Before
    fun setUp() {
        positionCalculator = PositionCalculator()
        commands = ArrayList<Command>()
    }

    @Test
    fun positionIsAtOriginWithNoCommands() {
        val position = Position(0, 0)
        assertEquals(position, positionCalculator.getPosition(commands))
    }

    @Test
    fun positionHandlesMovingForward() {
        commands.add(Command(CommandDirection.FORWARD, 2))
        val position = Position(2, 0)

        assertEquals(position, positionCalculator.getPosition(commands))
    }

    @Test
    fun positionDoesNotChangeWhenAdjustingAim() {
        commands.add(Command(CommandDirection.DOWN, 2))
        commands.add(Command(CommandDirection.DOWN, 4))
        commands.add(Command(CommandDirection.UP, 2))
        val position = Position(0, 0)

        assertEquals(position, positionCalculator.getPosition(commands))
    }

    @Test
    fun positionUpdatesWhenMovingForwardWithAimAdjustedDown() {
        commands.add(Command(CommandDirection.DOWN, 2))
        commands.add(Command(CommandDirection.FORWARD, 5))
        val position = Position(5, 10)

        assertEquals(position, positionCalculator.getPosition(commands))
    }

    @Test
    fun positionUpdatesWhenMovingForwardWithAimAdjustedUp() {
        commands.add(Command(CommandDirection.UP, 2))
        commands.add(Command(CommandDirection.FORWARD, 5))
        val position = Position(5, -10)

        assertEquals(position, positionCalculator.getPosition(commands))
    }
}