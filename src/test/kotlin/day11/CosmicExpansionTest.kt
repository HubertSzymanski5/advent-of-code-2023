package day11

import ExampleTest
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.TestInstance
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.MethodSource
import java.util.stream.Stream

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class CosmicExpansionTest : ExampleTest() {

    @Test
    fun shouldPassExamplePartI() {
        val input = inputReader.readFileMap("day11")
        val cosmicExpansion = CosmicExpansion(input)
        assertEquals(374, cosmicExpansion.findDistancesBetweenGalaxies().sum())
    }

    @ParameterizedTest
    @MethodSource("partIIArgumentsSource")
    fun shouldPassExamplePartII() {
        val input = inputReader.readFileMap("day11")
        val cosmicExpansion = CosmicExpansion(input)
        assertEquals(1030, cosmicExpansion.findDistancesBetweenGalaxies(10).sum())
    }

    private fun partIIArgumentsSource(): Stream<Arguments> = Stream.of(
        Arguments.of(10, 1030),
        Arguments.of(100, 8410)
    )
}