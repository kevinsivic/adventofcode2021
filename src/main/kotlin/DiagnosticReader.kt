import java.io.InputStream

class DiagnosticReader {
    private var horizontalData = mutableListOf<List<Int>>()

    fun readStream(stream: InputStream): MutableList<List<Int>> {
        val reader = stream.reader()
        reader.forEachLine { line ->
            val lineData = mutableListOf<Int>()
            line.forEach { char ->
                lineData.add(char.digitToInt())
            }
            horizontalData.add(lineData)
        }
        return horizontalData
    }

    fun getGammaRate(): Int {
        val bits = getEachColumnsMostCommonBits()
        return Integer.parseInt(bits, 2)
    }

    private fun getMostCommonBitFrom(colIndex: Int, searchData: MutableList<List<Int>>): Int {
        val column = searchData.fold(mutableListOf<Int>()) { accumulator,
                                                             row ->
            accumulator.add(row[colIndex])
            accumulator
        }
        val numberOf0 = column.count { char -> char == 0 }
        val numberOf1 = column.count { char -> char == 1 }
        return  if (numberOf0 > numberOf1)
                    0
                else
                    1
    }

    fun getEpsilonRate(): Int {
        val mostCommonBits = getEachColumnsMostCommonBits()
        val epsilonBits = invertBitString(mostCommonBits)
        return Integer.parseInt(epsilonBits, 2)
    }

    private fun getEachColumnsMostCommonBits(): String {
        var mostCommonBits = ""
        for (colIndex in horizontalData[0].indices)
            mostCommonBits += getMostCommonBitFrom(colIndex, horizontalData)
        return mostCommonBits
    }

    private fun invertBitString(original: String): String {
        var inverted = ""
        for (i in original.indices) {
            val originalAsInt = original[i].digitToInt()
            val invertedInt = if (originalAsInt == 0)
                1
            else
                0
            inverted += invertedInt.toString()
        }
        return inverted
    }

    fun getPowerConsumption(): Int {
        return getGammaRate() * getEpsilonRate()
    }

    fun removeEntriesWith(key: Int, index: Int, listToFilter: MutableList<List<Int>>): MutableList<List<Int>> {
        return listToFilter.filter {
            it[index] != key
        }.toMutableList()
    }

    fun getOxygenGeneratorReading(): Int {
        var data = horizontalData
        var i = 0
        while (data.count() != 1) {
            val mostCommon = getMostCommonBitFrom(i, data)
            val leastCommon = flipTheBit(mostCommon)
            data = removeEntriesWith(leastCommon, i, data)
            i++
            if (i == data[0].size)
                i = 0
        }

        return Integer.parseInt(data[0].joinToString(separator=""), 2)
    }

    private fun flipTheBit(mostCommon: Int): Int {
        return if (mostCommon == 1)
            0
        else
            1
    }

    fun getCO2ScrubberReading(): Int {
        var data = horizontalData
        var i = 0
        while (data.count() != 1) {
            val mostCommon = getMostCommonBitFrom(i, data)
            data = removeEntriesWith(mostCommon, i, data)
            i++
            if (i == data[0].size)
                i = 0
        }

        return Integer.parseInt(data[0].joinToString(separator=""), 2)
    }

    fun getLifeSupportRating(): Int {
        return getOxygenGeneratorReading() * getCO2ScrubberReading()
    }

}
