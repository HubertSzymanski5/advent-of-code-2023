package day19

data class Part(private val x: Int, private val m: Int, private val a: Int, private val s: Int) {

    fun fieldFrom(char: Char): Int {
        return when (char) {
            'x' -> x
            'm' -> m
            'a' -> a
            's' -> s
            else -> throw IllegalArgumentException("Cannot map field '$char'")
        }
    }

    fun ratingsSum(): Long = (x + m + a + s).toLong()

    companion object {
        fun from(input: String): Part {
            val (x, m, a, s) = input.drop(1).dropLast(1).split(",")
                .map { it.split("=").last().toInt() }
            return Part(x, m, a, s)
        }
    }
}