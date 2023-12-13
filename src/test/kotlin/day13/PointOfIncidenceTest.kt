package day13

import ExampleTest
import org.junit.jupiter.api.TestInstance
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.MethodSource
import java.util.stream.Stream
import kotlin.test.assertEquals

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class PointOfIncidenceTest : ExampleTest() {

    @ParameterizedTest
    @MethodSource("testCases")
    fun shouldPassExamples(smudges: Int, expected: Int) {
        val input = inputReader.readFileGroups("day13")
        val pointOfIncidence = PointOfIncidence.from(input)
        assertEquals(expected, pointOfIncidence.findMirrorsNumber(smudges))
    }

    private fun testCases(): Stream<Arguments> = Stream.of(
        Arguments.of(0, 405),
        Arguments.of(1, 400)
    )
}