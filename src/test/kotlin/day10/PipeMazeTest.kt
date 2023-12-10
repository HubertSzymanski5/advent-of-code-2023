package day10

import ExampleTest
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.TestInstance
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.MethodSource
import java.util.stream.Stream

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class PipeMazeTest : ExampleTest() {

    @ParameterizedTest
    @MethodSource("examplesPartI")
    fun shouldPassExamplesPartI(fileName: String, expectedSteps: Int) {
        val input = inputReader.readFileMap(fileName)
        val pipeMaze = PipeMaze(input)
        assertEquals(expectedSteps, pipeMaze.getStepsAlongTheLoopToAnimal())
    }

    @ParameterizedTest
    @MethodSource("examplesPartII")
    fun shouldPassExamplesPartII(fileName: String, expectedArea: Int) {
        val input = inputReader.readFileMap(fileName)
        val pipeMaze = PipeMaze(input)
        assertEquals(expectedArea, pipeMaze.getLoopArea())
    }

    private fun examplesPartI(): Stream<Arguments> = Stream.of(
        Arguments.of("day10-A", 4),
        Arguments.of("day10-B", 8)
    )

    private fun examplesPartII(): Stream<Arguments> = Stream.of(
        Arguments.of("day10-A", 1),
        Arguments.of("day10-B", 1),
        Arguments.of("day10-C", 4),
        Arguments.of("day10-D", 4),
        Arguments.of("day10-E", 8),
        Arguments.of("day10-F", 10)
    )
}