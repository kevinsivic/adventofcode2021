class DisplaySample(val patterns: List<String>, val values: List<String>) {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as DisplaySample

        if (patterns != other.patterns) return false
        if (values != other.values) return false

        return true
    }

    override fun hashCode(): Int {
        var result = patterns.hashCode()
        result = 31 * result + values.hashCode()
        return result
    }

    fun getDecodedOutput(): List<String> {
        return values.map {
            if (it.length == 2)
                "1"
            else if (it.length == 4)
                "4"
            else if (it.length == 3)
                "7"
            else if (it.length == 7)
                "8"
            else
                it
        }
    }

    fun getCodeForTopSegment(): Char {
        return getCodeByFrequencyInAllPatternsAndSimplePatterns(10)
    }

    fun getCodeForUpperLeftSegment(): Char {
        val possibleCodes = mutableMapOf('a' to 0, 'b' to 0, 'c' to 0, 'd' to 0, 'e' to 0, 'f' to 0, 'g' to 0)
        getCodeFrequencyInAllPatterns(possibleCodes)
        return getChosenPattern(possibleCodes, 6)
    }

    fun getCodeForUpperRightSegment(): Char {
        return getCodeByFrequencyInAllPatternsAndSimplePatterns(12)
    }

    fun getCodeForMiddleSegment(): Char {
        return getCodeByFrequencyInAllPatternsAndSimplePatterns(9)
    }

    fun getCodeForLowerLeftSegment(): Char {
        return getCodeByFrequencyInAllPatternsAndSimplePatterns(5)
    }

    fun getCodeForLowerRightSegment(): Char {
        return getCodeByFrequencyInAllPatternsAndSimplePatterns(13)
    }

    fun getCodeForBottomSegment(): Char {
        val possibleCodesAllPatterns = mutableMapOf('a' to 0, 'b' to 0, 'c' to 0, 'd' to 0, 'e' to 0, 'f' to 0, 'g' to 0)
        getCodeFrequencyInAllPatterns(possibleCodesAllPatterns)
        val possibleCodesSimplePatterns = mutableMapOf('a' to 0, 'b' to 0, 'c' to 0, 'd' to 0, 'e' to 0, 'f' to 0, 'g' to 0)
        getCodeFrequencyInSimplePatterns(possibleCodesSimplePatterns)

        val codesInAllPatternsSevenTimes = getAllCodesForFrequency(possibleCodesAllPatterns, 7)
        val codesInSimplePatternsOneTime = getAllCodesForFrequency(possibleCodesSimplePatterns, 1)
        return codesInAllPatternsSevenTimes.intersect(codesInSimplePatternsOneTime).first()
    }

    private fun getChosenPattern(possibleCodes: MutableMap<Char, Int>, frequency: Int) =
        getAllCodesForFrequency(possibleCodes, frequency).first()

    private fun getAllCodesForFrequency(
        possibleCodes: MutableMap<Char, Int>,
        frequency: Int
    ) = possibleCodes.filter { (_, count) ->
        count == frequency
    }.keys

    private fun countCodeAppearancesInPattern(
        pattern: String,
        codesToCount: MutableMap<Char, Int>
    ) {
        pattern.forEach { code ->
            if (codesToCount.containsKey(code))
                codesToCount[code] = codesToCount[code]!! + 1
        }
    }
    private fun getSimplePatterns() = patterns.filter { pattern ->
        pattern.length in listOf(2, 3, 4, 7)
    }

    private fun getCodeByFrequencyInAllPatternsAndSimplePatterns(frequency: Int): Char {
        val possibleCodes = mutableMapOf('a' to 0, 'b' to 0, 'c' to 0, 'd' to 0, 'e' to 0, 'f' to 0, 'g' to 0)
        getCodeFrequencyInAllPatterns(possibleCodes)
        getCodeFrequencyInSimplePatterns(possibleCodes)

        return getChosenPattern(possibleCodes, frequency)
    }

    private fun getCodeFrequencyInSimplePatterns(possibleCodes: MutableMap<Char, Int>) {
        getSimplePatterns().forEach { pattern ->
            countCodeAppearancesInPattern(pattern, possibleCodes)
        }
    }

    private fun getCodeFrequencyInAllPatterns(possibleCodes: MutableMap<Char, Int>) {
        patterns.forEach { pattern ->
            countCodeAppearancesInPattern(pattern, possibleCodes)
        }
    }

    fun getMapOfCodes(): Map<Char, Char> {
        return mapOf(
            'a' to getCodeForTopSegment(),
            'b' to getCodeForUpperLeftSegment(),
            'c' to getCodeForUpperRightSegment(),
            'd' to getCodeForMiddleSegment(),
            'e' to getCodeForLowerLeftSegment(),
            'f' to getCodeForLowerRightSegment(),
            'g' to getCodeForBottomSegment()
        )
    }

    fun getMappedOutputValues(): List<String> {
        val map = getMapOfCodes().entries.associate { (key, value) -> value to key}
        return values.map { value ->
            var mappedValue = ""
            value.toCharArray().forEach {
                mappedValue += map[it]
            }
            mappedValue
        }
    }

}
