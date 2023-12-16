package day15

class LensLibrary(private val initSequence: List<String>) {

    fun hashSum(): Int = initSequence.sumOf { adventHashOf(it) }

    fun calculateFocusingPower(): Int {
        return setLenses().mapIndexed { boxNum, boxLenses ->
            boxLenses.mapIndexed { slotNum, lens ->
                (boxNum + 1) * (slotNum + 1) * lens.split(":").last().toInt()
            }.sum()
        }.sum()
    }

    private fun setLenses(): List<List<String>> {
        val lenses = MutableList<MutableList<String>>(256) { mutableListOf() }
        initSequence.forEach { operation ->
            when {
                operation.contains("-") -> lenses.remove(operation)
                operation.contains("=") -> lenses.add(operation)
            }
        }
        println(lenses)
        return lenses
    }

    private fun MutableList<MutableList<String>>.remove(operation: String) {
        val lensKey = operation.dropLast(1)
        val index = adventHashOf(lensKey)
        this[index] = this[index].filter { !it.startsWith(lensKey) }.toMutableList()
    }

    private fun MutableList<MutableList<String>>.add(operation: String) {
        val (lensKey, focus) = operation.split("=")
        val index = adventHashOf(lensKey)
        this[index] = when {
            this[index].any { it.startsWith(lensKey) } -> this[index].map {
                if (it.startsWith(lensKey)) "$lensKey:$focus" else it
            }.toMutableList()

            else -> {
                this[index].add("$lensKey:$focus")
                this[index]
            }
        }
    }

    companion object {
        fun adventHashOf(string: String): Int {
            return string.fold(0) { result, char ->
                (result + char.code) * 17 % 256
            }
        }

        fun of(input: String): LensLibrary {
            val initSequence = input.split(",")
            return LensLibrary(initSequence)
        }
    }
}