import org.junit.Assert.assertEquals
import org.junit.Test

class DisplaySampleTest {
    @Test
    fun canDecodeSimpleValues() {
        val sampleWithOne = DisplaySample(listOf(), listOf("cdbfeg", "gaefc", "ae", "efgdb"))
        assertEquals(listOf("cdbfeg", "gaefc", "1", "efgdb"), sampleWithOne.getDecodedOutput())

        val sampleWithAllSimple = DisplaySample(listOf(), listOf("ae", "aefc", "aef", "abcdefg"))
        assertEquals(listOf("1", "4", "7", "8"), sampleWithAllSimple.getDecodedOutput())
    }

    @Test
    fun canFindTopSegment() {
        val listOfPatterns = "acedgfb cdfbe gcdfa fbcad dab cefabd cdfgeb eafb cagedb ab".split(" ")
        val outputValues = "cdfeb fcadb cdfeb cdbaf".split(" ")

        val displaySample = DisplaySample(listOfPatterns, outputValues)
        assertEquals("Could not find top segment", 'd', displaySample.getCodeForTopSegment())
        assertEquals("Could not find upper left segment",'e', displaySample.getCodeForUpperLeftSegment())
        assertEquals("Could not find upper right segment",'a', displaySample.getCodeForUpperRightSegment())
        assertEquals("Could not find middle segment",'f', displaySample.getCodeForMiddleSegment())
        assertEquals("Could not find lower left segment",'g', displaySample.getCodeForLowerLeftSegment())
        assertEquals("Could not find lower right segment",'b', displaySample.getCodeForLowerRightSegment())
        assertEquals("Could not find bottom segment",'c', displaySample.getCodeForBottomSegment())
    }

    @Test
    fun canGetMapOfCodes() {
        val listOfPatterns = "acedgfb cdfbe gcdfa fbcad dab cefabd cdfgeb eafb cagedb ab".split(" ")
        val outputValues = "cdfeb fcadb cdfeb cdbaf".split(" ")

        val displaySample = DisplaySample(listOfPatterns, outputValues)

        val map = mapOf('a' to 'd', 'b' to 'e', 'c' to 'a', 'd' to 'f', 'e' to 'g', 'f' to 'b', 'g' to 'c')
        assertEquals(map, displaySample.getMapOfCodes())
    }

    @Test
    fun canMapOutputValues() {
        val listOfPatterns = "acedgfb cdfbe gcdfa fbcad dab cefabd cdfgeb eafb cagedb ab".split(" ")
        val outputValues = "cdfeb fcadb cdfeb cdbaf".split(" ")

        val displaySample = DisplaySample(listOfPatterns, outputValues)

        val map = listOf("gadbf", "dgcaf", "gadbf", "gafcd")
        assertEquals(map, displaySample.getMappedOutputValues())
    }
}