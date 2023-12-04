package day04

import ExampleTest
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class ScratchCardCheckerTest : ExampleTest() {

    private val scratchCardChecker = ScratchCardChecker()

    @Test
    fun shouldPassExamplePartI() {
        val input = inputReader.readFileLines("day04")
        val result = scratchCardChecker.calculateTotalPointsFromCards(input)
        assertEquals(13, result)
    }

    @Test
    fun shouldPassExamplePartII() {
        val input = inputReader.readFileLines("day04")
        val result = scratchCardChecker.calculateTotalScratchCards(input)
        assertEquals(30, result)
    }
}