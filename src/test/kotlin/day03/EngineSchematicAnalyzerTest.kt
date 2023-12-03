package day03

import org.junit.jupiter.api.Test
import utils.InputReader
import kotlin.test.assertEquals

class EngineSchematicAnalyzerTest {

    private val inputReader = InputReader(true)
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