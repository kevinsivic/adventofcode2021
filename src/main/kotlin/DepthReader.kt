import java.io.InputStream
import java.io.InputStreamReader

class DepthReader {
    fun readStream(stream: InputStream): ArrayList<Int> {
        val depths = arrayListOf<Int>()
        val reader = InputStreamReader(stream)
        reader.forEachLine {
            depths.add(it.toInt())
        }

        return depths
    }
}
