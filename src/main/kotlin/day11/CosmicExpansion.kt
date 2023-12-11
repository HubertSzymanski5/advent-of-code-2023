package day11

import kotlin.math.abs

class CosmicExpansion(private val sky: Map<Pair<Int, Int>, Char>) {

    fun findDistancesBetweenGalaxies(expansionConstant: Long = 2): List<Long> {
        val expandedGalaxies = expandSky(expansionConstant)
        return expandedGalaxies
            .mapIndexed { index, currGalaxy ->
                expandedGalaxies.drop(index + 1).map { currGalaxy to it }
            }
            .flatten()
            .map { abs(it.first.first - it.second.first) + abs(it.first.second - it.second.second) }
    }

    private fun expandSky(expansionConstant: Long): Set<Pair<Long, Long>> {
        val galaxiesPositions = sky.getGalaxiesPositions()
        val expandedColumns = findLineToExpand(galaxiesPositions) { it.first }
        val expandedRows = findLineToExpand(galaxiesPositions) { it.second }

        return galaxiesPositions
            .map { it.getExpandedPosition(expandedColumns, expandedRows, expansionConstant) }
            .toSet()
    }

    private fun Pair<Long, Long>.getExpandedPosition(
        expandedColumns: Set<Long>,
        expandedRows: Set<Long>,
        expansionConstant: Long
    ): Pair<Long, Long> {
        val dx = expandedColumns.count { it < this.first }.toLong() * (expansionConstant - 1)
        val dy = expandedRows.count { it < this.second }.toLong() * (expansionConstant - 1)
        return this.first + dx to this.second + dy
    }

    private fun findLineToExpand(
        galaxiesPositions: Set<Pair<Long, Long>>,
        selector: (Pair<Long, Long>) -> Long
    ): Set<Long> {
        val lineWithGalaxies = galaxiesPositions
            .map { selector.invoke(it) }
            .toSet()
        val columnsToExpand = (0..lineWithGalaxies.max()).toSet() - lineWithGalaxies
        return columnsToExpand
    }

    private fun Map<Pair<Int, Int>, Char>.getGalaxiesPositions(): Set<Pair<Long, Long>> =
        this.entries
            .filter { it.value == '#' }
            .map { it.key }
            .map { it.first.toLong() to it.second.toLong() }
            .toSet()
}