package day21

import utils.Commons.Direction

import kotlin.math.pow

private typealias Position = Pair<Int, Int>

class StepCounter(private val gardenMap: Map<Position, Char>) {

    private val start: Position = findStartPosition()
    private val xSize: Int = gardenMap.keys.maxOf { it.first } + 1
    private val ySize: Int = gardenMap.keys.maxOf { it.second } + 1


    fun possiblePositionsAfterSteps(n: Int, initialSteps: Int = 0): Long {
        return if (n < xSize * 5) afterSteps(n) else afterLotOfSteps(n, initialSteps)
    }

    private fun afterLotOfSteps(n: Int, initialSteps: Int): Long {
        val reminder = n % xSize
        val points = mutableListOf<Pair<Int, Long>>()
        for (i in initialSteps..initialSteps + 2) {
            val nSteps = xSize * i + reminder
            val positions = afterSteps(nSteps)
            points.add(nSteps to positions)
        }
        val (a, b, c) = fitQuadratic(points)
        return (a * n.toDouble().pow(2) + b * n + c).toLong()
    }

    private fun afterSteps(n: Int): Long {
        val cache = mapOf(
            0 to mutableListOf(mutableSetOf(start), mutableSetOf(start)),
            1 to mutableListOf(mutableSetOf(), mutableSetOf())
        )
        (1..n).forEach { step ->
            val newPoints = cache[(step - 1) % 2]?.last()?.flatMap { currentPos ->
                Direction.getMainFour()
                    .map { currentPos.first + it.first to currentPos.second + it.second }
                    .filter { gardenMap.getRepeated(it) in ".S" }
            }?.toMutableSet()
            cache[step % 2]!![0].addAll(newPoints ?: setOf())
            cache[step % 2]!![1] = newPoints ?: mutableSetOf()
        }
        return cache[n % 2]!![0].count().toLong()
    }

    private fun fitQuadratic(points: List<Pair<Int, Long>>): Triple<Double, Double, Double> {

        val a = (points[2].second - 2 * points[1].second + points[0].second) / (points[2].first.toDouble()
            .pow(2) - 2 * points[1].first.toDouble().pow(2) + points[0].first.toDouble().pow(2))
        val b = (points[2].second - points[1].second - a * points[2].first.toDouble()
            .pow(2) + a * points[1].first.toDouble().pow(2)) / (points[2].first - points[1].first)
        val c = points[2].second - a * points[2].first.toDouble().pow(2) - b * points[2].first

        return Triple(a, b, c)
    }

    private fun Map<Position, Char>.getRepeated(pos: Position): Char =
        this[pos.first.mod(xSize) to pos.second.mod(ySize)] ?: throw RuntimeException("Cannot cast position $pos")

    private fun findStartPosition() = (gardenMap.entries.firstOrNull { it.value == 'S' }?.key
        ?: throw IllegalStateException("Cannot find start position"))


}