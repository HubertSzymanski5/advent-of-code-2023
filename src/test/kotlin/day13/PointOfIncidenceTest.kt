package day13

import ExampleTest
import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

class PointOfIncidenceTest : ExampleTest() {

    @Test
    fun shouldPassExamplePartI() {
        val input = inputReader.readFileGroups("day13")
        val pointOfIncidence = PointOfIncidence.from(input)
        assertEquals(405, pointOfIncidence.findMirrorsNumber())
    }

}