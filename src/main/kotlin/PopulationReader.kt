import java.io.InputStream

class PopulationReader(var stream: InputStream) {
    fun getTracker(): PopulationTracker {
        val tracker = PopulationTracker()
        stream.bufferedReader().readLine().split(",").forEach {
            tracker.add(LanternFish(it.toInt()))
        }
        return tracker
    }

}
