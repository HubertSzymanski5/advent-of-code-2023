package day07

import ExampleTest
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class CamelCardsTest : ExampleTest() {

    @Test
    fun shouldPassExamplePartI() {
        val input = inputReader.readFileLines("day07")
        val camelCards = CamelCards.from(input)
        assertEquals(6440, camelCards.calculateTotalWinnings())
    }

    @Test
    fun shouldPassExamplePartII() {
        val input = inputReader.readFileLines("day07")
        val camelCards = CamelCards.from(input)
        assertEquals(5905, camelCards.calculateTotalWinningsWithJokers())
    }

}