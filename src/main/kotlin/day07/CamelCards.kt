package day07

class CamelCards(private val hands: List<CamelHand>) {

    fun calculateTotalWinnings(): Int {
        return hands.sortedWith(CamelHandComparatorFactory.default())
            .mapIndexed { index, camelHand -> (index + 1) * camelHand.bid }
            .sum()
    }

    fun calculateTotalWinningsWithJokers(): Int {
        return hands.sortedWith(CamelHandComparatorFactory.withJokers())
            .onEach { println(it) }
            .mapIndexed { index, camelHand -> (index + 1) * camelHand.bid }
            .sum()
    }

    companion object {
        fun from(input: List<String>): CamelCards {
            val camelHands = input.map { CamelHand.of(it) }
            return CamelCards(camelHands)
        }
    }
}