import java.io.FileInputStream

fun main(args: Array<String>) {
    val diagnosticReader = DiagnosticReader()
    diagnosticReader.readStream(FileInputStream(args[0]))

    val lifeSupportRating = diagnosticReader.getLifeSupportRating()
    println("The life support rating is $lifeSupportRating.")
}