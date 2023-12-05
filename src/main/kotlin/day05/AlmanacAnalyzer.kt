package day05

class AlmanacAnalyzer(
    private val seeds: List<UInt>,
    private val mappings: List<AlmanacMapping>
) {
    fun getLowestLocationOfGivenSeeds(): UInt {
        return seeds.minOfOrNull { mappings.mapSeedToLocation(it) }!!
    }

    private fun List<AlmanacMapping>.mapSeedToLocation(seed: UInt): UInt {
        var currVal = seed
        this.forEach { currVal = it.getValue(currVal) }
        return currVal
    }

    companion object {
        fun fromGroups(input: List<String>): AlmanacAnalyzer {
            val seeds = input[0].split(": ").last().split(" ").map { it.toUInt() }
            val mappings: List<AlmanacMapping> = input.drop(1).map { AlmanacMapping.fromString(it) }
            return AlmanacAnalyzer(seeds, mappings)
        }
    }
}