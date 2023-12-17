package day17

import ExampleTest
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class ClumsyCrucibleTest : ExampleTest() {

    @Test
    fun shouldPassExamplePartI() {
        val input = inputReader.readFileMap("day17")
        val clumsyCrucible = ClumsyCrucible.from(input)
        assertEquals(102, clumsyCrucible.findMinimalHeatLoss())
    }

    @Test
    fun shouldPassExamplePartII() {
        val input = inputReader.readFileMap("day17")
        val clumsyCrucible = ClumsyCrucible.from(input)
        assertEquals(94, clumsyCrucible.findMinimalHeatLossForUltraCrucibles())
    }
}