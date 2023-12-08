package day07

interface CamelHandComparator : Comparator<CamelHand> {
    override fun compare(h1: CamelHand?, h2: CamelHand?): Int {
        if (h1 == null || h2 == null) {
            throw IllegalStateException("Cannot compare with null")
        }
        if (h1.getType() != h2.getType()) {
            return h1.getType().compareTo(h2.getType())
        }
        for (i in 0..<h1.cards.length) {
            if (h1.cards[i] != h2.cards[i])
                return valueOfCard(h1.cards[i]).compareTo(valueOfCard(h2.cards[i]))
        }
        return 0
    }

    fun CamelHand.getType(): HandType

    fun valueOfCard(char: Char): Int
}
