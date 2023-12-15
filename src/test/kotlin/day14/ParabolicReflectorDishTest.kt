package day14

import ExampleTest
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.TestInstance
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.MethodSource
import java.util.stream.Stream

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class ParabolicReflectorDishTest : ExampleTest() {

    @Test
    fun shouldPassExamplePartI() {
        val input = inputReader.readFileLines("day14")
        val parabolicReflectorDish = ParabolicReflectorDish(input)
        assertEquals(136, parabolicReflectorDish.calculateTotalLoad())
    }

    @Test
    fun shouldPassExamplePartII() {
        val input = inputReader.readFileLines("day14")
        val parabolicReflectorDish = ParabolicReflectorDish(input)
        assertEquals(64, parabolicReflectorDish.calculateTotalLoadAfterSpins())
    }

    @ParameterizedTest
    @MethodSource("crap")
    fun my(allCycles: Int, expected: Int) {
        val cycleStart = 3
        val cycleLen = 7
        val calculated = (allCycles - cycleStart) % cycleLen + cycleStart
        assertEquals(expected, calculated)
    }

    private fun crap(): Stream<Arguments> = Stream.of(
        Arguments.of(10, 3),
        Arguments.of(11, 4),
        Arguments.of(20, 6),
        Arguments.of(23, 9),
        Arguments.of(24, 3)
    )

    @ParameterizedTest
    @MethodSource("rotationsSet")
    fun shouldRotateAsInExample(spins: Int, state: List<String>) {
        val input = inputReader.readFileLines("day14")
        val parabolicReflectorDish = ParabolicReflectorDish(input)

        parabolicReflectorDish.spinTimes(spins)
        assertEquals(state, parabolicReflectorDish.rotated)
    }

    private fun List<String>.inverse(): List<String> {
        return List(this.first().length) { i -> this.joinToString("") { it[i].toString() } }
    }

    private fun rotationsSet(): Stream<Arguments> = Stream.of(
        Arguments.of(1, cycles[0]),
        Arguments.of(2, cycles[1]),
        Arguments.of(3, cycles[2])
    )

    private val cycles = listOf(
        """
            .....#....
            ....#...O#
            ...OO##...
            .OO#......
            .....OOO#.
            .O#...O#.#
            ....O#....
            ......OOOO
            #...O###..
            #..OO#....
        """.trimIndent(),
        """
            .....#....
            ....#...O#
            .....##...
            ..O#......
            .....OOO#.
            .O#...O#.#
            ....O#...O
            .......OOO
            #..OO###..
            #.OOO#...O
        """.trimIndent(),
        """
            .....#....
            ....#...O#
            .....##...
            ..O#......
            .....OOO#.
            .O#...O#.#
            ....O#...O
            .......OOO
            #...O###.O
            #.OOO#...O
        """.trimIndent()
    ).map { it.split("\n").inverse() }
}
