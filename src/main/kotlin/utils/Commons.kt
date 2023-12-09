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
}