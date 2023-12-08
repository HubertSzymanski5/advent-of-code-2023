package day07

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.TestInstance
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.MethodSource
import java.util.stream.Stream

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class HandTypeTest {

    @ParameterizedTest
    @MethodSource("handsSource")
    fun shouldReturnCorrectType(hand: String, type: HandType) {
        assertEquals(type, HandType.getTypeWithJokers(hand))
    }

    private fun handsSource(): Stream<Arguments> {
        return Stream.of(
            Arguments.of("32T3K", HandType.ONE_PAIR),
            Arguments.of("T55J5", HandType.FOUR_OF_A_KIND),
            Arguments.of("QQQJA", HandType.FOUR_OF_A_KIND),
            Arguments.of("KTJJT", HandType.FOUR_OF_A_KIND)
        )
    }


}