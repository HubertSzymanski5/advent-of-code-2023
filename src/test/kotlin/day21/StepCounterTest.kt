package day21

import ExampleTest
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.TestInstance
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.MethodSource
import java.util.stream.Stream


@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class StepCounterTest : ExampleTest() {

    @Test
    fun shouldPassExamplePartI() {
        val input = inputReader.readFileMap("day21")
        val stepCounter = StepCounter(input)
        assertEquals(16, stepCounter.possiblePositionsAfterSteps(6))
    }

    @ParameterizedTest
    @MethodSource("partIISource")
    fun shouldPassExamplePartII(steps: Int, expected: Long) {
        val input = inputReader.readFileMap("day21")
        val stepCounter = StepCounter(input)
        assertEquals(expected, stepCounter.possiblePositionsAfterSteps(steps, 5))
    }

    private fun partIISource(): Stream<Arguments> = Stream.of(
        Arguments.of(6, 16),
        Arguments.of(10, 50),
        Arguments.of(50, 1594),
        Arguments.of(100, 6536),
        Arguments.of(500, 167004),
        Arguments.of(1000, 668697),
        Arguments.of(5000, 16733044)
    )
}