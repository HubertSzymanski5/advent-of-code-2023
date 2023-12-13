package day12

import ExampleTest
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class HotSpringsTest : ExampleTest() {

    @Test
    fun shouldPassExamplePartI() {
        val input = inputReader.readFileLines("day12")
        val hotSprings = HotSprings.from(input)
        assertEquals(21, hotSprings.findAllPossibleCombinations())
    }

    @Test
    fun shouldPassExamplePartII() {
        val input = inputReader.readFileLines("day12")
        val hotSprings = HotSprings.from(input)
        assertEquals(525152, hotSprings.findAllPossibleCombinationsUnfolded())
    }

}