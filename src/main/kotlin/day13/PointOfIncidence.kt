package day13

class PointOfIncidence(private val grounds: List<List<String>>) {

    fun findMirrorsNumber(smudges: Int = 0) = grounds.sumOf { it.findMirrorValue(smudges) }

    private fun List<String>.findMirrorValue(smudges: Int): Int {
        return 100 * (this.findMirror(smudges) ?: 0) + (this.inverse().findMirror(smudges) ?: 0)
    }

    private fun List<String>.findMirror(smudges: Int): Int? {
        return (1..<size).firstOrNull { this.isPossibleMirror(it, smudges) }
    }

    private fun List<String>.isPossibleMirror(num: Int, smudges: Int): Boolean {
        val radius = num.coerceAtMost(size - num)
        val leading = this.subList(num - radius, num)
        val following = this.subList(num, num + radius).reversed()
        return leading.zip(following)
            .sumOf { it.first.zip(it.second).count { (a, b) -> a != b } } == smudges
    }

    private fun List<String>.inverse(): List<String> =
        List(this.first().length) { i -> this.joinToString("") { it[i].toString() } }


    companion object {
        fun from(inputs: List<String>): PointOfIncidence {
            val grounds = inputs.map { it.split("\n") }
            return PointOfIncidence(grounds)
        }
    }
}