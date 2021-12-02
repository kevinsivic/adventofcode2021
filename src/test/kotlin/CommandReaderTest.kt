import org.junit.Assert.assertEquals
import org.junit.Test

class CommandReaderTest {
    @Test
    fun commandsAreReadToAnArrayList() {
        val commands = ArrayList<Command>()
        commands.add(Command(CommandDirection.DOWN, 1,))
        commands.add(Command(CommandDirection.FORWARD, 5,))

        val inputString = """
            down 1
            forward 5
        """.trimIndent()
        val input = inputString.byteInputStream()

        assertEquals(commands, CommandReader().readStream(input))
    }

    @Test
    fun canHandleUpCommands() {
        val commands = ArrayList<Command>()
        commands.add(Command(CommandDirection.UP, 2))

        val inputString = """
            up 2
        """.trimIndent()
        val input = inputString.byteInputStream()

        assertEquals(commands, CommandReader().readStream(input))
    }
}