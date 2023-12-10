package day10

import utils.Commons.Direction
import utils.Commons.Direction.E
import utils.Commons.Direction.N
import utils.Commons.Direction.S
import utils.Commons.Direction.W

class PipeMaze(private val map: Map<Pair<Int, Int>, Char>) {

    private val start by lazy { findStart() }
    private val startChar by lazy { findStartChar() }
    private val loop by lazy { findLoop() }
    private val clearedMap by lazy { map.mapWithLoopOnly() }

    fun getStepsAlongTheLoopToAnimal(): Int {
        return loop.size / 2
    }

    fun getLoopArea(): Int {
        return clearedMap.filter { it.value == '.' }
            .map { it.key }
            .count { isInsideTheLoop(it) }
    }

    private fun isInsideTheLoop(point: Pair<Int, Int>): Boolean {
        val cutChars = mutableSetOf('|', 'L', 'J')
        if (startChar in cutChars) cutChars.add('S')
        val cuts = clearedMap
            .filter { it.key.second == point.second && it.key.first >= point.first }
            .map { it.value }
            .count { it in cutChars }
        return cuts % 2 == 1
    }

    private fun Map<Pair<Int, Int>, Char>.mapWithLoopOnly() =
        this.entries.associate { if (loop.contains(it.key)) it.key to it.value else it.key to '.' }

    private fun findLoop(): List<Pair<Int, Int>> {
        val loop = mutableSetOf<Pair<Int, Int>>()
        var newPoints: Set<Pair<Int, Int>> = setOf(start)
        do {
            loop.addAll(newPoints)
            newPoints = newPoints
                .map { findPipesConnectedTo(it) }
                .flatten()
                .filter { !loop.contains(it) }
                .toSet()
        } while (newPoints.isNotEmpty())
        return loop.toList()
    }

    private fun findStart(): Pair<Int, Int> = map.entries.filter { it.value == 'S' }.map { it.key }.first()

    private fun findStartChar(): Char {
        val connectedByStart = findPipesConnectedTo(start)
            .map { it.first - start.first to it.second - start.second }
            .toSet()
        return listOf('|', '-', 'L', 'J', '7', 'F').find { pipeConnections(it).toSet() == connectedByStart }
            ?: throw IllegalStateException("Cannot combine $connectedByStart into one pipe element")
    }

    private fun findPipesConnectedTo(position: Pair<Int, Int>): List<Pair<Int, Int>> {
        return pipeConnections(map[position]!!)
            .asSequence()
            .map { it.first + position.first to it.second + position.second } // neighbours
            .map { it to map[it] } // neighbour and its pipe
            .filter { it.second != null } // remove invalid neighbours
            .map { it.first to pipeConnections(it.second!!) } // get neighbour connections
            .filter { hasConnectionWithPosition(it, position) }
            .map { it.first }
            .toList()
    }

    private fun hasConnectionWithPosition(
        neighbourToConnections: Pair<Pair<Int, Int>, List<Pair<Int, Int>>>,
        position: Pair<Int, Int>
    ) = neighbourToConnections.second
        .any { con ->
            (con.first + neighbourToConnections.first.first) to (con.second + neighbourToConnections.first.second) == position
        }

    private fun pipeConnections(char: Char): List<Pair<Int, Int>> {
        return when (char) {
            'S' -> Direction.getMainFour()
            '|' -> listOf(N.cord, S.cord)
            '-' -> listOf(W.cord, E.cord)
            'L' -> listOf(N.cord, E.cord)
            'J' -> listOf(N.cord, W.cord)
            '7' -> listOf(S.cord, W.cord)
            'F' -> listOf(S.cord, E.cord)
            else -> listOf()
        }
    }
}