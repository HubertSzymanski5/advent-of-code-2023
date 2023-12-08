package day06

import kotlin.math.ceil
import kotlin.math.sqrt

class BoatRace(private val raceRecords: List<Pair<Long, Long>>, private val oneRaceRecord: Pair<Long, Long>) {

    fun calculateErrorMargin(): Long = raceRecords.map { it.waysToBeatTheRecord() }
        .reduce { acc, el -> acc * el }

    fun calculateErrorMarginForOneRace(): Long = oneRaceRecord.waysToBeatTheRecord()

    private fun Pair<Long, Long>.waysToBeatTheRecord(): Long {
        val solutions = solveQuadraticEquation(this.first, this.second)
        return solutions.last() - solutions.first + 1
    }

    private fun solveQuadraticEquation(totalTime: Long, recordDistance: Long): LongRange {
        val delta = totalTime * totalTime - 4 * recordDistance
        val sqrtDelta = sqrt(delta.toDouble())
        val min = ((totalTime - sqrtDelta) / 2.0 + 1).toLong()
        val max = ceil((totalTime + sqrtDelta) / 2.0 - 1).toLong()
        return min..max
    }

    companion object {
        fun from(input: List<String>): BoatRace {
            val (times, distances) = input.map { line ->
                line.split(Regex("( )+")).drop(1).map { it.toLong() }
            }
            val oneRecord = times.joinToString(separator = "") { it.toString() }.toLong() to
                    distances.joinToString(separator = "") { it.toString() }.toLong()
            return BoatRace(times.zip(distances), oneRecord)
        }
    }
}