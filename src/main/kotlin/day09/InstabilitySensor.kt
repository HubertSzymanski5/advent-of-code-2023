package day09

data class InstabilitySensor(private val sensorsInputs: List<List<Int>>) {

    fun findExtrapolatedValues(): List<Int> = sensorsInputs.map { extrapolateNextValue(it) }

    fun findExtrapolatedBackwardsValues(): List<Int> = sensorsInputs
        .map { it.reversed() }
        .map { extrapolateNextValue(it) }

    private fun extrapolateNextValue(sensorInputs: List<Int>): Int {
        val sequences = mutableListOf<MutableList<Int>>()
        sequences.add(sensorInputs.toMutableList())
        while (!sequences.last().all { it == 0 }) {
            sequences.add(sequences.last().getListOfDiffs())
        }
        sequences.last().add(0)
        (sequences.size - 2 downTo 0).forEach { index ->
            sequences[index].add(sequences[index].last() + sequences[index + 1].last())
        }
        return sequences.first().last()
    }

    private fun List<Int>.getListOfDiffs(): MutableList<Int> =
        this.windowed(2, 1).map { it.last() - it.first() }.toMutableList()

    companion object {
        fun from(input: List<String>): InstabilitySensor {
            val sensorsInputs = input.filter { it.isNotBlank() }
                .map { it.split(" ").map(String::toInt) }
            return InstabilitySensor(sensorsInputs)
        }
    }
}