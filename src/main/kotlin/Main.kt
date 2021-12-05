import java.io.FileInputStream

fun main(args: Array<String>) {
    val boardReader = BoardReader().readStream(FileInputStream(args[0]))
    boardReader.getLosingBoard()
    println("The winning boards score is " + boardReader.getLosingScore() + ".")
}