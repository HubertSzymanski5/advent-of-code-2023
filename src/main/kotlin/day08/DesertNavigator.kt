package day08

import utils.Commons
import java.util.function.Predicate

class DesertNavigator(private val graph: Graph, private val moves: String) {

    fun findNumberOfSteps(): Long = findNumbersOfStepsFromTo("AAA") { it == "ZZZ" }

    fun findNumberOfStepsForGhosts(): Long = graph.nodes.keys
        .filter { it.last() == 'A' }
        .map { findNumbersOfStepsFromTo(it) { node -> node.last() == 'Z' } }
        .reduce { acc, next -> Commons.lcm(acc, next) }

    private fun findNumbersOfStepsFromTo(start: String, endCondition: Predicate<String>): Long {
        val moveSequence = createMoveSequenceGenerator()
        var steps = 0L
        var currentNode = start
        do {
            steps++
            when (moveSequence.next()) {
                'L' -> currentNode = graph.goLeftFrom(currentNode)
                'R' -> currentNode = graph.goRightFrom(currentNode)
            }
        } while (!endCondition.test(currentNode))
        return steps
    }

    private fun createMoveSequenceGenerator() = generateSequence(moves.indices.iterator()) {
        if (it.hasNext()) it else moves.indices.iterator()
    }.map { moves[it.next()] }.iterator()

    companion object {
        fun from(input: List<String>): DesertNavigator {
            val (moves, graph) = input
            return DesertNavigator(Graph.from(graph), moves)
        }
    }
}