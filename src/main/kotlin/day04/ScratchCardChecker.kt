package day04

class ScratchCardChecker {

    fun calculateTotalPointsFromCards(cardLines: List<String>): Int {
        return cardLines
            .map { ScratchCard.fromString(it) }
            .sumOf { it.cardPoints() }
    }

    fun calculateTotalScratchCards(cardLines: List<String>): Int {
        val cardsWins = cardLines.map { ScratchCard.fromString(it) }
            .mapIndexed { index, card -> Pair(index + 1, card.wonCopies()) }
            .toMap()
        val cardCopies = mutableMapOf(1 to 0)
        cardsWins.entries.forEach { entry ->
            cardCopies.addOrIncrement(entry.key, 1)
            addNextCards(entry.key, entry.value, cardCopies)
        }
        return cardCopies.values.sum()
    }

    private fun addNextCards(currentCardId: Int, numOfCardsToAdd: Int, cardCopiesMap: MutableMap<Int, Int>) {
        val multiplier = cardCopiesMap[currentCardId]!!
        for (i in (currentCardId + 1)..currentCardId + numOfCardsToAdd)
            cardCopiesMap.addOrIncrement(i, multiplier)
    }

    private fun MutableMap<Int, Int>.addOrIncrement(key: Int, numOfCardsToAdd: Int) {
        this[key] = (this[key] ?: 0) + numOfCardsToAdd
    }
}