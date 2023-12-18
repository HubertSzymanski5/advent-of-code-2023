package day18

import utils.Commons
import utils.Commons.Direction.*
import kotlin.math.abs

private typealias Direction = Commons.Direction
private typealias Position = Pair<Long, Long>

class LavaDuctLagoon(private val digPlan: List<Edge>) {

    private val vertices: List<Position> by lazy { mapDigPlanToVertices(digPlan) }
    private val correctedDigPlan: List<Edge> by lazy { decodeCorrectDigPlan() }
    private val correctedVertices: List<Position> by lazy { mapDigPlanToVertices(correctedDigPlan) }

    fun calculateVolume(): Long = calculateVolume(digPlan, vertices)

    fun calculateCorrectedVolume(): Long = calculateVolume(correctedDigPlan, correctedVertices)

    private fun calculateVolume(plan: List<Edge>, vertices: List<Position>): Long {
        val steps = plan.sumOf { it.length }
        return abs(vertices.windowed(2)
            .sumOf { (a, b) -> (a.first + b.first) * (a.second - b.second) }) / 2 + steps / 2 + 1
    }

    private fun mapDigPlanToVertices(plan: List<Edge>): List<Position> {
        var current = 0L to 0L
        return plan.map {
            current += (it.direction.longCord() * it.length)
            current
        }
    }

    private fun decodeCorrectDigPlan(): List<Edge> {
        return digPlan.map { it.color }
            .map {
                val hex = it.take(5)
                val dir = when (it.last()) {
                    '0' -> E
                    '1' -> S
                    '2' -> W
                    '3' -> N
                    else -> throw IllegalStateException("Illegal direction ${it.last()}")
                }
                Edge(dir, hex.toLong(16), "")
            }
    }

    private operator fun Position.plus(other: Position): Position =
        this.first + other.first to this.second + other.second

    private operator fun Position.times(other: Long): Position =
        this.first * other to this.second * other

    companion object {
        fun from(input: List<String>): LavaDuctLagoon {
            val edges = input.map {
                val (dir, steps, color) = it.split(" ")
                Edge(dirFrom(dir), steps.toLong(), color.drop(2).dropLast(1))
            }
            return LavaDuctLagoon(edges)
        }

        private fun dirFrom(value: String): Direction =
            when (value) {
                "U" -> N
                "D" -> S
                "L" -> W
                "R" -> E
                else -> throw IllegalStateException("Illegal direction $value")
            }
    }
}

data class Edge(val direction: Direction, val length: Long, val color: String)