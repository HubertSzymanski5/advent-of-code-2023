package day14

class ParabolicReflectorDish(private val mountain: List<String>) {

    private val columns by lazy { mountain.inverse() }
    var rotated = columns
    private val cache = mutableMapOf<List<String>, Int>()
    private var cyclesDone = 0

    fun calculateTotalLoad(): Int = calculateLoadOnNorth(rotated.move())

    fun calculateTotalLoadAfterSpins(): Int {
        spinTimes(1_000_000_000)
        return calculateLoadOnNorth(rotated)
    }

    private fun calculateLoadOnNorth(fromPosition: List<String>): Int {
        val distance = fromPosition.size
        return fromPosition.sumOf { it.calculateSingleLoad(distance) }
    }

    fun spinTimes(cycles: Int) {
        while (!cache.containsKey(rotated) && cyclesDone < cycles) {
            cache[rotated] = cyclesDone
            rotated = rotated
                .move().rotate()                  // N
                .move().rotate()                  // W
                .move().rotate()                  // S
                .move().rotate()                  // E
            cyclesDone++
        }
        if (cyclesDone < cycles) {
            val cycleStart = cache[rotated]!!
            val cycleLen = cyclesDone - cycleStart
            val expectedRot = (cycles - cycleStart) % cycleLen + cycleStart

            rotated = cache.entries.associate { it.value to it.key }[expectedRot]!!
        }
    }

    private fun List<String>.rotate(): List<String> = this.map { it.reversed() }.inverse()

    private fun List<String>.move(): List<String> = this.map { it.moveColumn() }

    private fun String.moveColumn(): String = this.split("#")
        .joinToString("#") { it.toList().sortedDescending().joinToString("") { c -> c.toString() } }

    private fun String.calculateSingleLoad(distance: Int): Int {
        return this.mapIndexed { index, c -> index to c }
            .filter { it.second == 'O' }
            .sumOf { distance - it.first }
    }

    private fun List<String>.inverse(): List<String> {
        return List(this.first().length) { i -> this.joinToString("") { it[i].toString() } }
    }
}

