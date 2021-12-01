import org.junit.Assert.assertEquals
import org.junit.Test
import java.io.FileInputStream

class DepthReaderTest {
    @Test
    fun depthMeasurementsAreReadToAnArrayList() {
        val input = """
            209
            210
            212
            198
            215
        """.trimIndent()
        val measurements = arrayListOf(209, 210, 212, 198, 215)

        val stream = input.byteInputStream()
        assertEquals(measurements, DepthReader().readStream(stream))
    }
}