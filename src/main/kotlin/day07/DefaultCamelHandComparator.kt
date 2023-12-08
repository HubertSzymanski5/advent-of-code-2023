package day07

class DefaultCamelHandComparator : CamelHandComparator {

    override fun CamelHand.getType(): HandType = this.type

    override fun valueOfCard(char: Char): Int = when (char) {
        'A' -> 14
        'K' -> 13
        'Q' -> 12
        'J' -> 11
        'T' -> 10
        in '2'..'9' -> char.digitToInt()
        else -> throw IllegalArgumentException("Unknown card: $char")
    }
}