package day02

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import utils.InputReader

class CubeGameValidatorTest {

    val inputReader = InputReader(true)
    val gameValidator = CubeGameValidator()

    @Test
    fun shouldPassExamplePartI() {
        val input = inputReader.readFileLines("day02")
        val result = gameValidator.findPossibleGames(input)
        assertEquals(listOf(1, 2, 5), result)
    }

    @Test
    fun shouldPassExamplePartII() {
        val input = inputReader.readFileLines("day02")
        val result = gameValidator.findFewestNumberForEachGame(input)
        val expected = listOf(
            Triple(4, 2, 6),
            Triple(1, 3, 4),
            Triple(20, 13, 6),
            Triple(14, 3, 15),
            Triple(6, 3, 2)
        )
        assertEquals(expected, result)
    }

    @Test
    fun shouldCalculatePowers() {
        val input = inputReader.readFileLines("day02")
        val result = gameValidator.findPowersOfFewestNumberForEachGame(input)
        val expected = listOf(48, 12, 1560, 630, 36)
        assertEquals(expected, result)
    }

}