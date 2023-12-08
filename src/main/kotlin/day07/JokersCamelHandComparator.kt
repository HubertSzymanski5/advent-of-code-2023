package day07

class JokersCamelHandComparator : CamelHandComparator {

    override fun CamelHand.getType(): HandType = this.typeWithJokers

    override fun valueOfCard(char: Char): Int = when (char) {
        'A' -> 13
        'K' -> 12
        'Q' -> 11
        'T' -> 10
        in '2'..'9' -> char.digitToInt()
        'J' -> 1
        else -> throw IllegalArgumentException("Unknown card: $char")
    }
}