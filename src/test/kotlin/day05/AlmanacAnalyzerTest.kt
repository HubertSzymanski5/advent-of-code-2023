package day05

import ExampleTest
import org.junit.jupiter.api.Assertions.assertEquals
import kotlin.test.Test

class AlmanacAnalyzerTest : ExampleTest() {

    @Test
    fun shouldPassExamplePartI() {
        val input = inputReader.readFileGroups("day05")
        val analyzer = AlmanacAnalyzer.fromGroups(input)
        val result = analyzer.getLowestLocationOfGivenSeeds()
        assertEquals(35L, result)
    }

    @Test
    fun shouldPassExamplePartII() {
        val input = inputReader.readFileGroups("day05")
        val analyzer = AlmanacAnalyzer.fromGroups(input)
        val result = analyzer.getLowestLocationOfGivenRangesOfSeeds()
        assertEquals(46L, result)
    }

    @Test
    fun rangesStuff() {
        val map1 = mapOf(
            (0..<50) to (0..<50),
            (50..<98) to (52..<100),
            (98..<100) to (50..<52)
        )

        val map2 = mapOf(
            (0..<37) to (39..<52),
            (37..<39) to (52..<54),
            (39..<54) to (0..<15),
            (54..<100) to (54..<100)
        )

        fun Map<IntRange, IntRange>.getRanges(range: IntRange): Map<IntRange, IntRange> {
            val foundRange = this.entries.find { range.first in it.key }!!
            val startDiff = range.first - foundRange.key.first // this should be always positive
            val endDiff = foundRange.key.last - range.last
            // if ends equal
            if (endDiff == 0) {
                return mapOf(range to foundRange.value.first + startDiff..foundRange.value.last)
            }
            // if ends before
            if (endDiff > 0) {
                return mapOf(range to foundRange.value.first + startDiff..foundRange.value.last - endDiff)
            }
            // if ends after
            return mapOf(
                range.first..range.last + endDiff to foundRange.value.first + startDiff..foundRange.value.last
            ) + this.getRanges(range.last + endDiff + 1..range.last)
        }

//        println(map2.getRanges(30..50))

        val sth = map1.entries.associate { it.key to map2.getRanges(it.value) }


    }
}