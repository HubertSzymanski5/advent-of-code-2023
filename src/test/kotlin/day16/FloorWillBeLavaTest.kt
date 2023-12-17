package day16

import ExampleTest
import org.junit.jupiter.api.Assertions.assertEquals
import kotlin.test.Test

class FloorWillBeLavaTest : ExampleTest() {

    @Test
    fun shouldPassExamplePartI() {
        val input = inputReader.readFileMap("day16")
        val floorWillBeLava = FloorWillBeLava(input)
        assertEquals(46, floorWillBeLava.getNumOfEnergizedTiles())
    }

    @Test
    fun shouldPassExamplePartII() {
        val input = inputReader.readFileMap("day16")
        val floorWillBeLava = FloorWillBeLava(input)
        assertEquals(51, floorWillBeLava.getMaxNumOfEnergizedTiles())
    }
}