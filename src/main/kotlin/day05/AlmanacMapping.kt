package day05

class AlmanacMapping(private val mapping: Map<UIntRange, UInt>) {

    fun getValue(key: UInt): UInt {
        return mapping.entries
            .filter { (range, _) -> key in range }
            .throwIfMoreThanOneElement()
            .map { it.value + key - it.key.first }
            .firstOrNull() ?: key
    }

    private fun <T> List<T>.throwIfMoreThanOneElement(): List<T> {
        if (this.size > 1) throw IllegalStateException("Matched too many ranges!")
        return this
    }

    companion object {
        fun fromString(input: String): AlmanacMapping {
            val mapping = input
                .split(":\n")
                .last()
                .split("\n")
                .map { mappingLine ->
                    mappingLine
                        .split(" ")
                        .map { it.toUInt() }
                }
                .associate { (destStart, sourceStart, len) -> Pair(sourceStart..<sourceStart + len, destStart) }
            return AlmanacMapping(mapping)
        }
    }

}