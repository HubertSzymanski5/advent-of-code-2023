package day19

import ExampleTest
import kotlin.test.Test
import kotlin.test.assertEquals

class AplentyTest : ExampleTest() {

    @Test
    fun shouldPassExamplePartI() {
        val input = inputReader.readFileGroups("day19")
        val aplenty = Aplenty.from(input)
        assertEquals(19114L, aplenty.collectiveRatingOfAccepted())
    }

    @Test
    fun shouldPassExamplePartII() {
        val input = inputReader.readFileGroups("day19")
        val aplenty = Aplenty.from(input)
        assertEquals(167409079868000L, aplenty.getNumberOfDistinctRatingsAccepted())
    }
}