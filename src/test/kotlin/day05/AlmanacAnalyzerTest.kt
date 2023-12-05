package day05

import ExampleTest
import org.junit.jupiter.api.Assertions.assertEquals
import kotlin.test.Test

class AlmanacAnalyzerTest : ExampleTest() {

    @Test
    fun shouldPassExamplePartI() {
        val input = inputReader.readFileGroups("day05")
        val analyzer = AlmanacAnalyzer.fromGroups(input)
        val result = analyzer.getLowestLocationOfGivenSeeds()
        assertEquals(35U, result)
    }
}