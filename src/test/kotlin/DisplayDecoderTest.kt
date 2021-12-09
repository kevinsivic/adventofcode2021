import org.junit.Assert.assertEquals
import org.junit.Test

class DisplayDecoderTest {

    @Test
    fun canGetNextSample() {
        val outputValues = listOf("fdgacbe","cefdb","cefbgd","gcbe")
        val samples = listOf("be","cfbegad","cbdgef","fgaecd","cgeb","fdcge","agebfd","fecdb","fabcd","edb")
        val sample = DisplaySample(samples,outputValues)
        val stream = """
            be cfbegad cbdgef fgaecd cgeb fdcge agebfd fecdb fabcd edb | fdgacbe cefdb cefbgd gcbe
            edbfga begcd cbg gc gcadebf fbgde acbgfd abcde gfcbed gfec | fcgedb cgb dgebacf gc
            fgaebd cg bdaec gdafb agbcfd gdcbef bgcad gfac gcb cdgabef | cg cg fdcagb cbg
            fbegcd cbd adcefb dageb afcb bc aefdc ecdab fgdeca fcdbega | efabcd cedba gadfec cb
            aecbfdg fbg gf bafeg dbefa fcge gcbea fcaegb dgceab fcbdga | gecf egdcabf bgf bfgea
            fgeab ca afcebg bdacfeg cfaedg gcfdb baec bfadeg bafgc acf | gebdcfa ecba ca fadegcb
            dbcfg fgd bdegcaf fgec aegbdf ecdfab fbedc dacgb gdcebf gf | cefg dcbef fcge gbcadfe
            bdfegc cbegaf gecbf dfcage bdacg ed bedf ced adcbefg gebcd | ed bcgafe cdgba cbgef
            egadfb cdbfeg cegd fecab cgb gbdefca cg fgcdab egfdb bfceg | gbdfcae bgc cg cgb
            gcafb gcf dcaebfg ecagb gf abcdeg gaef cafbge fdbac fegbdc | fgae cfgab fg bagce
        """.trimIndent().byteInputStream()
        val decoder = DisplayDecoder(stream)
        assertEquals(sample, decoder.getSample(0))
    }

    @Test
    fun countsSimpleOutputValues() {
        val stream = """
            be cfbegad cbdgef fgaecd cgeb fdcge agebfd fecdb fabcd edb | fdgacbe cefdb cefbgd gcbe
            edbfga begcd cbg gc gcadebf fbgde acbgfd abcde gfcbed gfec | fcgedb cgb dgebacf gc
            fgaebd cg bdaec gdafb agbcfd gdcbef bgcad gfac gcb cdgabef | cg cg fdcagb cbg
            fbegcd cbd adcefb dageb afcb bc aefdc ecdab fgdeca fcdbega | efabcd cedba gadfec cb
            aecbfdg fbg gf bafeg dbefa fcge gcbea fcaegb dgceab fcbdga | gecf egdcabf bgf bfgea
            fgeab ca afcebg bdacfeg cfaedg gcfdb baec bfadeg bafgc acf | gebdcfa ecba ca fadegcb
            dbcfg fgd bdegcaf fgec aegbdf ecdfab fbedc dacgb gdcebf gf | cefg dcbef fcge gbcadfe
            bdfegc cbegaf gecbf dfcage bdacg ed bedf ced adcbefg gebcd | ed bcgafe cdgba cbgef
            egadfb cdbfeg cegd fecab cgb gbdefca cg fgcdab egfdb bfceg | gbdfcae bgc cg cgb
            gcafb gcf dcaebfg ecagb gf abcdeg gaef cafbge fdbac fegbdc | fgae cfgab fg bagce
        """.trimIndent().byteInputStream()
        val decoder = DisplayDecoder(stream)
        assertEquals(26, decoder.getCountOfSimpleValues())
    }

    @Test
    fun canTranslateValueToNumber() {
        val decoder = DisplayDecoder("".byteInputStream())

        assertEquals(0, decoder.translateValue("abcefg"))
        assertEquals(1, decoder.translateValue("cf"))
        assertEquals(2, decoder.translateValue("acdeg"))
        assertEquals(3, decoder.translateValue("acdfg"))
        assertEquals(4, decoder.translateValue("bcdf"))
        assertEquals(5, decoder.translateValue("abdfg"))
        assertEquals(6, decoder.translateValue("abdefg"))
        assertEquals(7, decoder.translateValue("acf"))
        assertEquals(8, decoder.translateValue("abcdefg"))
        assertEquals(9, decoder.translateValue("abcdfg"))
    }

    @Test
    fun canTranslateValueToNumberOutOfOrder() {
        val decoder = DisplayDecoder("".byteInputStream())
        assertEquals(1, decoder.translateValue("fc"))
    }

    @Test
    fun canTranslateScrambledNumbers() {
        val decoder = DisplayDecoder("acedgfb cdfbe gcdfa fbcad dab cefabd cdfgeb eafb cagedb ab | cdfeb fcadb cdfeb cdbaf".byteInputStream())
        assertEquals(5353, decoder.translateScrambledOutput())
    }
}