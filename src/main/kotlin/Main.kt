import java.io.FileInputStream

fun main(args: Array<String>) {
    val depthReader = DepthReader()
    val measurements = depthReader.readStream(FileInputStream(args[0]))

    val measurementCounter = MeasurementCounter()
    println("There are " + measurementCounter.countIncreasing(measurements, 1) + " increasing measurements.")
}