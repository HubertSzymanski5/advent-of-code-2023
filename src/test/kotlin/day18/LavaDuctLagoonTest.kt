package day18

import ExampleTest
import org.junit.jupiter.api.Assertions.assertEquals
import kotlin.test.Test

class LavaDuctLagoonTest : ExampleTest() {

    @Test
    fun shouldPassSimpleSquare() {
        /*
        that should create
        #####
        #   #
        #   #
        #   #
        #####
        Area should be 25
         */
        val lavaDuctLagoon = LavaDuctLagoon.from(
            listOf(
                "R 4 (#fff)",
                "D 4 (#fff)",
                "L 4 (#fff)",
                "U 4 (#fff)"
            )
        )
        assertEquals(25L, lavaDuctLagoon.calculateVolume())
    }

    @Test
    fun shouldPassExamplePartI() {
        val input = inputReader.readFileLines("day18")
        val lavaDuctLagoon = LavaDuctLagoon.from(input)
        assertEquals(62L, lavaDuctLagoon.calculateVolume())
    }

    @Test
    fun shouldPassExamplePartII() {
        val input = inputReader.readFileLines("day18")
        val lavaDuctLagoon = LavaDuctLagoon.from(input)
        assertEquals(952408144115L, lavaDuctLagoon.calculateCorrectedVolume())
    }
}