package day06

import ExampleTest
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.BeforeAll
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.TestInstance
import org.junit.jupiter.api.TestInstance.Lifecycle.PER_CLASS

@TestInstance(PER_CLASS)
class BoatRaceTest : ExampleTest() {

    private lateinit var boatRace: BoatRace

    @BeforeAll
    fun setupExamplesScenario() {
        val input = inputReader.readFileLines("day06")
        boatRace = BoatRace.from(input)
    }

    @Test
    fun shouldPassExamplePartI() {
        assertEquals(288, boatRace.calculateErrorMargin())
    }

    @Test
    fun shouldPassExamplePartII() {
        assertEquals(71503, boatRace.calculateErrorMarginForOneRace())
    }
}