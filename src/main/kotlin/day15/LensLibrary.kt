package day15

class LensLibrary(private val initSequence: List<String>) {

    fun hashSum(): Int = initSequence.sumOf { adventHashOf(it) }

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