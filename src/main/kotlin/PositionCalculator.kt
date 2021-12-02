import java.util.ArrayList

class PositionCalculator {
    private var aim = 0

    fun getPosition(commands: ArrayList<Command>): Position {
        val position = Position(0,0)
        for (command in commands) {
            when (command.direction) {
                CommandDirection.FORWARD -> {
                    position.horizontalPosition += command.distance
                    position.verticalPosition += command.distance * aim
                }
                CommandDirection.DOWN -> aim += command.distance
                CommandDirection.UP -> aim -= command.distance
            }
        }

        return position
    }

}
