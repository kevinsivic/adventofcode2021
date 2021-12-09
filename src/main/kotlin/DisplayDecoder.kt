import java.io.InputStream
import java.io.InputStreamReader

class DisplayDecoder(var stream: InputStream) {

    private var displaySamples: MutableList<DisplaySample> = mutableListOf()

    init {
        InputStreamReader(stream).forEachLine { line ->
            val(patterns, values) = line.split('|').map {
                it.trim().split(" ")
            }
            displaySamples.add(DisplaySample(patterns, values))
        }
    }

    fun getSample(index: Int): DisplaySample {
        return displaySamples[index]
    }

    fun getCountOfSimpleValues(): Int {
        var count = 0
        displaySamples.forEach { sample ->
            count += sample.values.count { value ->
                value.length in listOf(2,3,4,7)
            }
        }
        return count
    }

    fun translateValue(segments: String): Int {
        return when (segments.toSortedSet().joinToString("")) {
            "abcefg" -> 0
            "cf" -> 1
            "acdeg" -> 2
            "acdfg" -> 3
            "bcdf" -> 4
            "abdfg" -> 5
            "abdefg" -> 6
            "acf" -> 7
            "abcdefg" -> 8
            "abcdfg" -> 9
            else -> -1
        }
    }

    fun translateScrambledOutput(): List<Int> {
        val outputValues = mutableListOf<Int>()
        displaySamples.forEach { sample ->
            var stringOutput = ""
            sample.getMappedOutputValues().forEach { value ->
                stringOutput += translateValue(value)
            }
            outputValues.add(stringOutput.toInt())
        }
        return outputValues
    }
}
