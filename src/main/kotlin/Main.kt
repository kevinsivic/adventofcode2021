import java.io.FileInputStream

fun main(args: Array<String>) {
    val commandReader = CommandReader()
    val commands = commandReader.readStream(FileInputStream(args[0]))

    val position = PositionCalculator().getPosition(commands)
    println("The sub is at horizontal position " + position.horizontalPosition +
            " and vertical posiiton " + position.verticalPosition + ".")
}