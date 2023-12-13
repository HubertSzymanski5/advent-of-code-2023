package day13

class PointOfIncidence(private val grounds: List<Map<Pair<Int, Int>, Char>>) {

    fun findMirrorsNumber() = grounds.sumOf { it.findMirror() }

    private fun Map<Pair<Int, Int>, Char>.findMirror(): Int {
        val maxX = this.keys.maxOf { it.first }
        val maxY = this.keys.maxOf { it.second }

        val verticalMirror = (0..maxY).map { y ->
            (0..maxX).map { x ->
                this[x to y]
            }.joinToString(separator = "") { it.toString() }
        }.map { possibleMirror(it) }
            .reduce { acc, possibleMirrors -> acc intersect possibleMirrors }
            .singleOrNull()
        return if (verticalMirror == null) {
            (0..maxX).map { x ->
                (0..maxY).map { y ->
                    this[x to y]
                }.joinToString(separator = "") { it.toString() }
            }.map { possibleMirror(it) }
                .reduce { acc, possibleMirrors -> acc intersect possibleMirrors }
                .single() * 100
        } else {
            verticalMirror
        }

    }

    private fun possibleMirror(input: String): Set<Int> {
        return (1..<input.length).map {
            val left = input.substring(0..<it)
            val right = input.substring(it..<input.length)
            when {
                left.length == right.length -> it to (left == right.reversed())
                left.length < right.length -> it to (left == right.take(left.length).reversed())
                else -> it to (left.reversed().take(right.length) == right)
            }
        }
            .filter { it.second }
            .map { it.first }
            .toSet()
    }

    companion object {
        fun from(inputs: List<String>): PointOfIncidence {
            val grounds = inputs.map { readToMap(it) }
        return PointOfIncidence(grounds)
        }

        private fun readToMap(input: String): Map<Pair<Int, Int>, Char> {
            return input.split("\n")
                .flatMapIndexed { y, line ->
                    line.mapIndexed { x, char -> (x to y) to char }
                }
                .toMap()
        }
    }

}