import java.io.InputStream
import java.io.InputStreamReader

class CommandReader {
    fun readStream(stream: InputStream): ArrayList<Command> {
        val commands = arrayListOf<Command>()
        val reader = InputStreamReader(stream)
        reader.forEachLine {
            val split = it.split(" ")

            val direction = parseDirection(split[0])
            val distance = parseDistance(split)

            commands.add(Command(direction, distance,))
        }

        return commands
    }

    private fun parseDistance(split: List<String>) = split[1].toInt()

    private fun parseDirection(directionString: String): CommandDirection {
        val direction: CommandDirection?
        if (directionString == "forward")
            direction = CommandDirection.FORWARD
        else if (directionString == "down")
            direction = CommandDirection.DOWN
        else
            direction = CommandDirection.UP
        return direction
    }

}
