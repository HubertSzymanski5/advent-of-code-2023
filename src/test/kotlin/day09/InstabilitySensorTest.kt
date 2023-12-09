package day09

import ExampleTest
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class InstabilitySensorTest : ExampleTest() {

    @Test
    fun shouldPassExamplePartI() {
        val input = inputReader.readFileLines("day09")
        val instabilitySensor = InstabilitySensor.from(input)
        assertEquals(114, instabilitySensor.findExtrapolatedValues().sum())
    }

    @Test
    fun shouldPassExamplePartII() {
        val input = inputReader.readFileLines("day09")
        val instabilitySensor = InstabilitySensor.from(input)
        assertEquals(2, instabilitySensor.findExtrapolatedBackwardsValues().sum())
    }
}