package day15

import ExampleTest
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class LensLibraryTest : ExampleTest() {

    @Test
    fun shouldHashString() {
        val string = "HASH"
        assertEquals(52, LensLibrary.adventHashOf(string))
    }

    @Test
    fun shouldPassExamplePartI() {
        val input = inputReader.readFileLines("day15").first()
        val lensLibrary = LensLibrary.of(input)
        assertEquals(1320, lensLibrary.hashSum())
    }

    @Test
    fun shouldPassExamplePartII() {
        val input = inputReader.readFileLines("day15").first()
        val lensLibrary = LensLibrary.of(input)
        assertEquals(145, lensLibrary.calculateFocusingPower())
    }
}