package day08

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class GraphTest {

    @Test
    fun shouldProperlyReadMap() {
        val graph = Graph.from(
            """
            AAA = (BBB, CCC)
            BBB = (DDD, EEE)
        """.trimIndent()
        )
        val expected = mapOf("AAA" to ("BBB" to "CCC"), "BBB" to ("DDD" to "EEE"))
        assertEquals(expected, graph.nodes)
    }

    @Test
    fun shouldProperlyMoveAround() {
        val graph = Graph.from(
            """
            AAA = (BBB, CCC)
            BBB = (BBB, CCC)
            CCC = (AAA, BBB)
        """.trimIndent()
        )
        assertEquals("BBB", graph.goLeftFrom("AAA"))
        assertEquals("CCC", graph.goRightFrom("AAA"))
        assertEquals("BBB", graph.goLeftFrom("BBB"))
        assertEquals("CCC", graph.goRightFrom("BBB"))
        assertEquals("AAA", graph.goLeftFrom("CCC"))
        assertEquals("BBB", graph.goRightFrom("CCC"))
    }
}