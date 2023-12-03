package day03

class EngineSchematicAnalyzer {

    private val digitsCharsRange = '0'..'9'

    fun findPartNumbersSum(engineSchematic: Map<Pair<Int, Int>, Char>): Int {
        val partsCoords = findParts(engineSchematic)
        val partNumbers = partsCoords
            .map { findPartNumbersAroundPart(it, engineSchematic) }
            .flatten()
        return partNumbers.sum()
    }

    fun findGearRatiosSum(engineSchematic: Map<Pair<Int, Int>, Char>): Int {
        return engineSchematic
            .filter { it.value == '*' }
            .map { it.key }
            .map { findPartNumbersAroundPart(it, engineSchematic) }
            .filter { it.size == 2 }
            .sumOf { it.reduce { acc, i -> acc * i } }
    }

    private fun findParts(engineSchematic: Map<Pair<Int, Int>, Char>) =
        engineSchematic
            .filter { it.value != '.' && it.value !in digitsCharsRange }
            .map { it.key }


    private fun findPartNumbersAroundPart(
        partCoords: Pair<Int, Int>,
        engineSchematic: Map<Pair<Int, Int>, Char>
    ): List<Int> {
        val neighboursDirs = listOf(
            Pair(-1, -1), Pair(0, -1), Pair(1, -1),
            Pair(-1, 0),    /* part */      Pair(1, 0),
            Pair(-1, 1), Pair(0, 1), Pair(1, 1)
        )
        val neighbourDigits: Map<Pair<Int, Int>, Char> = neighboursDirs
            .map { Pair(it.first + partCoords.first, it.second + partCoords.second) }
            .filter { engineSchematic.containsKey(it) }
            .associateWith { engineSchematic[it]!! }
            .filter { it.value in digitsCharsRange }
        return neighbourDigits.entries
            .map { findNumberWithDigit(it, engineSchematic) }
            .distinctBy { it.first }
            .map { it.second }
    }

    private fun findNumberWithDigit(
        digitEntry: Map.Entry<Pair<Int, Int>, Char>,
        engineSchematic: Map<Pair<Int, Int>, Char>
    ): Pair<Pair<Int, Int>, Int> {
        var beginningCord = digitEntry.key
        fun iterateAround(cord: Pair<Int, Int>, dir: Int): String {
            val nextCord = Pair(cord.first + dir, cord.second)
            val nextVal = engineSchematic[nextCord]
            if (nextVal in digitsCharsRange) {
                when (dir) {
                    1 -> return "$nextVal${iterateAround(nextCord, dir)}"
                    -1 -> {
                        beginningCord = nextCord
                        return "${iterateAround(nextCord, dir)}$nextVal"
                    }
                }
            }
            return ""
        }

        val num = "${iterateAround(digitEntry.key, -1)}${digitEntry.value}${iterateAround(digitEntry.key, 1)}".toInt()
        return Pair(beginningCord, num)
    }
}