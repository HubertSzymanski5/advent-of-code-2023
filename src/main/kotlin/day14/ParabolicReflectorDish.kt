package day14

class ParabolicReflectorDish(private val mountain: List<String>) {

    private val nsDistance by lazy { mountain.first().length }
    private val columns by lazy { mountain.inverse() }

    fun calculateTotalLoad(): Int = columns.sumOf { it.calculateSingleLoad() }

    private fun String.calculateSingleLoad(): Int {
        val weights = listOf(nsDistance) + this.mapIndexed { index, c -> index to c }
            .filter { it.second == '#' }
            .map { nsDistance - it.first - 1 }
        val groups = this.split("#")
        return groups.zip(weights)
            .sumOf { weightForSection(it.first, it.second) }
    }

    private fun weightForSection(section: String, startWeight: Int): Int {
        val rollingRocksCount = section.count { it == 'O' }
        return (2 * startWeight - (rollingRocksCount - 1)) * rollingRocksCount / 2
    }

    private fun List<String>.inverse(): List<String> {
        return List(this.first().length) { i -> this.joinToString("") { it[i].toString() } }
    }
}