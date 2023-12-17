package day17

import utils.Commons
import utils.Commons.Direction.*
import java.util.*

private typealias Position = Pair<Int, Int>
private typealias Direction = Commons.Direction

class ClumsyCrucible(private val cityBlocks: Map<Position, Int>) {

    fun findMinimalHeatLoss(minForward: Int = 0, maxForward: Int = 3): Int {
        val toVisit = PriorityQueue<Pair<Crucible, Int>>(compareBy { it.second })
        val visited = hashSetOf<Crucible>()
        val ends = mutableListOf<Int>()
        val end = findEndPoint()
        toVisit.add(Crucible(0 to 0, E, 0) to 0)
        toVisit.add(Crucible(0 to 0, S, 0) to 0)

        while (toVisit.isNotEmpty()) {
            val state = toVisit.poll()
            if (state.first in visited) continue
            if (state.first.position == end) {
                ends.add(state.second)
                continue
            }
            visited.add(state.first)
            if (state.first.repeats < maxForward) {
                handleMoveForward(state, toVisit)
            }
            if (state.first.repeats >= minForward) {
                leftAndRightOf(state.first.direction).forEach {
                    handleTurn(state, it, toVisit)
                }
            }
        }
        return ends.min()
    }

    fun findMinimalHeatLossForUltraCrucibles(): Int = findMinimalHeatLoss(4, 10)

    private fun handleTurn(
        state: Pair<Crucible, Int>,
        turnedTo: Direction,
        toVisit: PriorityQueue<Pair<Crucible, Int>>
    ) {
        val nextPos = state.first.position + turnedTo.cord
        if (cityBlocks.containsKey(nextPos)) {
            toVisit.add(
                Crucible(nextPos, turnedTo, 1) to state.second + cityBlocks[nextPos]!!
            )
        }
    }

    private fun handleMoveForward(
        state: Pair<Crucible, Int>,
        toVisit: PriorityQueue<Pair<Crucible, Int>>
    ) {
        val nextPos = state.first.position + state.first.direction.cord
        if (cityBlocks.containsKey(nextPos)) {
            toVisit.add(
                Crucible(
                    nextPos,
                    state.first.direction,
                    state.first.repeats + 1
                ) to state.second + cityBlocks[nextPos]!!
            )
        }
    }

    private fun leftAndRightOf(direction: Commons.Direction): Set<Direction> =
        when (direction) {
            N, S -> setOf(E, W)
            E, W -> setOf(N, S)
            else -> throw IllegalStateException("Illegal direction: $direction")
        }


    private fun findEndPoint(): Position {
        val maxX = cityBlocks.keys.maxOf { it.first }
        val maxY = cityBlocks.keys.maxOf { it.second }
        return maxX to maxY
    }

    private operator fun Pair<Int, Int>.plus(other: Pair<Int, Int>): Pair<Int, Int> {
        return this.first + other.first to this.second + other.second
    }

    companion object {
        fun from(input: Map<Position, Char>): ClumsyCrucible {
            val blocks = input.entries.associate { it.key to it.value.digitToInt() }
            return ClumsyCrucible(blocks)
        }
    }
}

data class Crucible(val position: Position, val direction: Direction, val repeats: Int)