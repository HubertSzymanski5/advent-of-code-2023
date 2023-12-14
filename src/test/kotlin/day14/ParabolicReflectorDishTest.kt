package day14

import ExampleTest
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.TestInstance

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class ParabolicReflectorDishTest : ExampleTest() {

    @Test
    fun shouldPassExamplePartI() {
        val input = inputReader.readFileLines("day14")
        val parabolicReflectorDish = ParabolicReflectorDish(input)
        assertEquals(136, parabolicReflectorDish.calculateTotalLoad())
    }

//    @ParameterizedTest
//    @MethodSource("weirdExamples")
//    fun shouldPassWeirdExamples(input: List<String>, expected: Int) {
//        val parabolicReflectorDish = ParabolicReflectorDish(input)
//        assertEquals(expected, parabolicReflectorDish.calculateTotalLoad())
//    }
//
//    private fun weirdExamples(): Stream<Arguments> = Stream.of(
//        Arguments.of(listOf("#O"), 1)
//    )

    private fun List<String>.inverse(): List<String> {
        return List(this.first().length) { i -> this.joinToString("") { it[i].toString() } }
    }
}
