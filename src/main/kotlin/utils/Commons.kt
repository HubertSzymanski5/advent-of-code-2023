package utils

class Commons {

    companion object {
        fun gcd(val1: Long, val2: Long): Long {
            var temp: Long
            var a = val1
            var b = val2
            while (b != 0L) {
                temp = b
                b = a % b
                a = temp
            }
            return a
        }

        fun lcm(val1: Long, val2: Long): Long = (val1 * val2) / gcd(val1, val2)
    }

    enum class Direction(val cord: Pair<Int, Int>) {
        NW(-1 to -1), N(0 to -1), NE(1 to -1),
        W(-1 to 0), E(1 to 0),
        SW(-1 to 1), S(0 to 1), SE(1 to 1);

        companion object {
            fun getAll() = Direction.entries.map { it.cord }
            fun getMainFour() = listOf(N, W, E, S).map { it.cord }
        }
    }
}