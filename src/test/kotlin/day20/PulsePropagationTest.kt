package day20

import ExampleTest
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.TestInstance
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.MethodSource
import java.util.stream.Stream

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class PulsePropagationTest : ExampleTest() {

    @ParameterizedTest
    @MethodSource("partISource")
    fun shouldPassExamplePartI(file: String, expected: Long) {
        val input = inputReader.readFileLines(file)
        val pulsePropagation = PulsePropagation.from(input)
        assertEquals(expected, pulsePropagation.getMultiplyOfSignals())
    }

    private fun partISource(): Stream<Arguments> = Stream.of(
        Arguments.of("day20-A", 32000000L),
        Arguments.of("day20-B", 11687500L),
    )
}