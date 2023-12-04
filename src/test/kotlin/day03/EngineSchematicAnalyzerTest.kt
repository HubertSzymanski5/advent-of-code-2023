package day03

import ExampleTest
import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

class EngineSchematicAnalyzerTest: ExampleTest() {

    private val engineSchematicAnalyzer = EngineSchematicAnalyzer()

    @Test
    fun shouldPassExamplePartI() {
        val input = inputReader.readFileMap("day03")
        val result = engineSchematicAnalyzer.findPartNumbersSum(input)
        assertEquals(4361, result)
    }

    @Test
    fun shouldPassExamplePartII() {
        val input = inputReader.readFileMap("day03")
        val result = engineSchematicAnalyzer.findGearRatiosSum(input)
        assertEquals(467835, result)
    }
}