package day16

import ExampleTest
import org.junit.jupiter.api.Assertions.*
import kotlin.test.Test

class FloorWillBeLavaTest : ExampleTest() {


    @Test
    fun shouldPassExamplePartI() {
        val input = inputReader.readFileMap("day16")
        val floorWillBeLava = FloorWillBeLava(input)
        floorWillBeLava.getNumOfEnergizedTiles()
    }
}