package day05

data class RangePair(
    private val source: LongRange,
    private val destination: LongRange
) {
    fun translate(num: Long): Long = destination.first + num - source.first

    fun flip(): RangePair = RangePair(destination, source)

    operator fun contains(num: Long): Boolean = num in source

    companion object {
        fun of(row: String): RangePair {
            val parts = row.split(" ").map { it.toLong() }
            return RangePair(
                parts[1]..<parts[1] + parts[2],
                parts[0]..<parts[0] + parts[2]
            )
        }
    }
}