package day19

data class PartRange(
    private val x: IntRange,
    private val m: IntRange,
    private val a: IntRange,
    private val s: IntRange
) {
    fun copyWithReplace(char: Char, range: IntRange): PartRange {
        val ranges = mutableMapOf('x' to x, 'm' to m, 'a' to a, 's' to s)
        ranges[char] = range
        return PartRange(ranges['x']!!, ranges['m']!!, ranges['a']!!, ranges['s']!!)
    }

    fun fieldFrom(char: Char): IntRange {
        return when (char) {
            'x' -> x
            'm' -> m
            'a' -> a
            's' -> s
            else -> throw IllegalArgumentException("Cannot map field '$char'")
        }
    }

    fun combinations(): Long = x.count().toLong() * m.count().toLong() * a.count().toLong() * s.count().toLong()
}
