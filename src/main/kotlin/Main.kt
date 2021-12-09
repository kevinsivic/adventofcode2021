import java.io.FileInputStream

fun main(args: Array<String>) {
    val displayDecoder = DisplayDecoder(FileInputStream(args[0]))
    val outputs = displayDecoder.translateScrambledOutput()
    var sum = 0
    outputs.forEach {
        sum += it
    }
    println("The total of all outputs is $sum.")
}