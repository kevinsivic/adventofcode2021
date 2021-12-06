import java.io.FileInputStream

fun main(args: Array<String>) {
    val populationTracker = PopulationReader(FileInputStream(args[0])).getTracker()
    populationTracker.simulate(256)
    println("The number of fish after 256 days is " + populationTracker.getCurrentPopulation() + ".")
}