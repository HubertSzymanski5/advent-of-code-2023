package day12

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.TestInstance
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.MethodSource
import java.util.stream.Stream

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class ReportRowTest {

    @ParameterizedTest
    @MethodSource("folded")
    fun shouldPassExamplesI(springs: String, groups: List<Int>, expectation: Int) {
        val reportRow = ReportRow(springs, groups)
        assertEquals(expectation, reportRow.findAllPossibleCombinations())
    }

    @ParameterizedTest
    @MethodSource("unfolded")
    fun shouldPassExamplesII(springs: String, groups: List<Int>, expectation: Int) {
        val reportRow = ReportRow(springs, groups)
        assertEquals(expectation, reportRow.findAllUnfoldedPossibleCombinations())
    }

    private fun folded(): Stream<Arguments> = Stream.of(
//        Arguments.of("???.###", listOf(1, 1, 3), 1),
//        Arguments.of(".??..??...?##.", listOf(1, 1, 3), 4),
        Arguments.of("?#?#?#?#?#?#?#?", listOf(1, 3, 1, 6), 1),
//        Arguments.of("????.#...#...", listOf(4, 1, 1), 1),
//        Arguments.of("????.######..#####.", listOf(1, 6, 5), 4),
//        Arguments.of("?###????????", listOf(3, 2, 1), 10)
    )

    private fun unfolded(): Stream<Arguments> = Stream.of(
        Arguments.of("???.###", listOf(1, 1, 3), 1),
        Arguments.of(".??..??...?##.", listOf(1, 1, 3), 16384),
        Arguments.of("?#?#?#?#?#?#?#?", listOf(1, 3, 1, 6), 1),
        Arguments.of("????.#...#...", listOf(4, 1, 1), 16),
        Arguments.of("????.######..#####.", listOf(1, 6, 5), 2500),
        Arguments.of("?###????????", listOf(3, 2, 1), 506250)
    )
}