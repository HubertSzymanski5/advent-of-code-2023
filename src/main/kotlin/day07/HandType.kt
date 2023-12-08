package day07

enum class HandType {
    HIGH_CARD,
    ONE_PAIR,
    TWO_PAIR,
    THREE_OF_A_KIND,
    FULL_HOUSE,
    FOUR_OF_A_KIND,
    FIVE_OF_A_KIND;

    companion object {
        fun getType(cards: String): HandType {
            val occurrences = cards
                .groupBy { it }
                .map { it.value.size }
            return when (occurrences.size) {
                1 -> FIVE_OF_A_KIND
                2 -> if (occurrences.contains(4)) FOUR_OF_A_KIND else FULL_HOUSE
                3 -> if (occurrences.contains(3)) THREE_OF_A_KIND else TWO_PAIR
                4 -> ONE_PAIR
                5 -> HIGH_CARD
                else -> throw IllegalStateException("Hand has more than 5 cards: ${occurrences.size}")
            }
        }

        fun getTypeWithJokers(cards: String): HandType {
            val occurrences = cards.groupBy { it }
                .mapValues { it.value.size }
            val jokers = occurrences['J'] ?: 0
            val occurrencesWithoutJokers = occurrences.filter { it.key != 'J' }
                .map { it.value }
                .sortedDescending()
                .toMutableList()
            if (occurrencesWithoutJokers.isEmpty()) {
                return FIVE_OF_A_KIND
            }
            occurrencesWithoutJokers[0] += jokers
            println("$cards: $occurrencesWithoutJokers")
            return when (occurrencesWithoutJokers.size) {
                1 -> FIVE_OF_A_KIND
                2 -> if (occurrencesWithoutJokers.contains(4)) FOUR_OF_A_KIND else FULL_HOUSE
                3 -> if (occurrencesWithoutJokers.contains(3)) THREE_OF_A_KIND else TWO_PAIR
                4 -> ONE_PAIR
                5 -> HIGH_CARD
                else -> throw IllegalStateException("Hand has more than 5 cards: ${occurrencesWithoutJokers.size}")
            }
        }
    }
}