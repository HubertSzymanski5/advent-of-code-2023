package day08

import ExampleTest
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.TestInstance
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.MethodSource
import java.util.stream.Stream

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class DesertNavigatorTest : ExampleTest() {

    @ParameterizedTest
    @MethodSource("partIExamples")
    fun shouldPassExamplePartI(fileName: String, expectedSteps: Long) {
        val desertNavi = DesertNavigator.from(inputReader.readFileGroups(fileName))
        assertEquals(expectedSteps, desertNavi.findNumberOfSteps())
    }

    @Test
    fun shouldPassExamplePartII() {
        val input = inputReader.readFileGroups("day08-C")
        val desertNavigator = DesertNavigator.from(input)
        assertEquals(6L, desertNavigator.findNumberOfStepsForGhosts())
    }

    private fun partIExamples(): Stream<Arguments> = Stream.of(
        Arguments.of("day08-A", 2L),
        Arguments.of("day08-B", 6L)
    )
}