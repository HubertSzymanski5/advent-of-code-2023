package day07

data class CamelHand(val cards: String, val bid: Int) {

    val type by lazy { HandType.getType(cards) }
    val typeWithJokers by lazy { HandType.getTypeWithJokers(cards) }

    companion object {
        fun of(line: String): CamelHand {
            val (cards, bid) = line.split(" ")
            return CamelHand(cards, bid.toInt())
        }
    }
}

