import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import java.io.ByteArrayInputStream

class DiagnosticReaderTest {
    private lateinit var stream: ByteArrayInputStream
    private lateinit var diagnosticReader: DiagnosticReader

    @Before
    fun setUp() {
        diagnosticReader = DiagnosticReader()

        val fileContents = """
            00100
            11110
            10110
            10111
            10101
            01111
            00111
            11100
            10000
            11001
            00010
            01010
        """.trimIndent()

        stream = fileContents.byteInputStream()
    }

    @Test
    fun canReadDiagnosticData() {
        val diagnostics = MutableList(12) {mutableListOf<Int>() }

        diagnostics[0] = mutableListOf(0,0,1,0,0)
        diagnostics[1] = mutableListOf(1,1,1,1,0)
        diagnostics[2] = mutableListOf(1,0,1,1,0)
        diagnostics[3] = mutableListOf(1,0,1,1,1)
        diagnostics[4] = mutableListOf(1,0,1,0,1)
        diagnostics[5] = mutableListOf(0,1,1,1,1)
        diagnostics[6] = mutableListOf(0,0,1,1,1)
        diagnostics[7] = mutableListOf(1,1,1,0,0)
        diagnostics[8] = mutableListOf(1,0,0,0,0)
        diagnostics[9] = mutableListOf(1,1,0,0,1)
        diagnostics[10] = mutableListOf(0,0,0,1,0)
        diagnostics[11] = mutableListOf(0,1,0,1,0)

        assertEquals(diagnostics, diagnosticReader.readStream(stream))
    }

    @Test
    fun canCalculateGammaRate() {
        val gammaRate = 22
        diagnosticReader.readStream(stream)

        assertEquals(gammaRate, diagnosticReader.getGammaRate())
    }

    @Test
    fun canCalculateEpsilonRate() {
        val gammaRate = 9
        diagnosticReader.readStream(stream)

        assertEquals(gammaRate, diagnosticReader.getEpsilonRate())
    }

    @Test
    fun canCalculatePowerConsumption() {
        val powerConsumption = 198
        diagnosticReader.readStream(stream)

        assertEquals(powerConsumption, diagnosticReader.getPowerConsumption())
    }

    @Test
    fun canRemoveEntriesWithGivenSecondBit() {
        val updatedBits = MutableList(7) {
            mutableListOf<Int>()
        }

        updatedBits[0] = mutableListOf(0,0,1,0,0)
        updatedBits[1] = mutableListOf(1,0,1,1,0)
        updatedBits[2] = mutableListOf(1,0,1,1,1)
        updatedBits[3] = mutableListOf(1,0,1,0,1)
        updatedBits[4] = mutableListOf(0,0,1,1,1)
        updatedBits[5] = mutableListOf(1,0,0,0,0)
        updatedBits[6] = mutableListOf(0,0,0,1,0)
        assertEquals(updatedBits, diagnosticReader.removeEntriesWith(1, 1, diagnosticReader.readStream(stream)))
    }

    @Test
    fun canRemoveEntriesWithGivenFirstBit() {
        val updatedBits = MutableList(5) {
            mutableListOf<Int>()
        }

        updatedBits[0] = mutableListOf(0,0,1,0,0)
        updatedBits[1] = mutableListOf(0,1,1,1,1)
        updatedBits[2] = mutableListOf(0,0,1,1,1)
        updatedBits[3] = mutableListOf(0,0,0,1,0)
        updatedBits[4] = mutableListOf(0,1,0,1,0)
        diagnosticReader.readStream(stream)
        assertEquals(updatedBits, diagnosticReader.removeEntriesWith(1, 0, diagnosticReader.readStream(stream)))
    }

    @Test
    fun canCalculateOxygenGeneratorRating() {
        val oxygenGeneratorRating = 23
        diagnosticReader.readStream(stream)
        assertEquals(oxygenGeneratorRating, diagnosticReader.getOxygenGeneratorReading())
    }

    @Test
    fun canCalculateCO2ScrubberRating() {
        val oxygenGeneratorRating = 10
        diagnosticReader.readStream(stream)
        assertEquals(oxygenGeneratorRating, diagnosticReader.getCO2ScrubberReading())
    }

    @Test
    fun canCalculateLifeSupportRating() {
        val oxygenGeneratorRating = 230
        diagnosticReader.readStream(stream)
        assertEquals(oxygenGeneratorRating, diagnosticReader.getLifeSupportRating())
    }
}