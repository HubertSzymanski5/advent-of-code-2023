package day07

class CamelHandComparatorFactory {
    companion object {
        fun default(): CamelHandComparator = DefaultCamelHandComparator()
        fun withJokers(): CamelHandComparator = JokersCamelHandComparator()
    }
}