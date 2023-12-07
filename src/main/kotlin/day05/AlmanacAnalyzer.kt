package day05

class AlmanacAnalyzer(
    private val seeds: List<Long>,
    private val functions: List<Set<RangePair>>,
) {
    fun getLowestLocationOfGivenSeeds(): Long {
        return seeds.minOf { seed ->
            functions.fold(seed) { acc, ranges ->
                ranges.firstOrNull { acc in it }?.translate(acc) ?: acc
            }
        }
    }

    fun getLowestLocationOfGivenRangesOfSeeds(): Long {
        val rangesReversed = functions.map { ranges ->
            ranges.map { it.flip() }
        }.reversed()

        return generateSequence(0L, Long::inc).first { location ->
            val seed = rangesReversed.fold(location) { acc, ranges ->
                ranges.firstOrNull { acc in it }?.translate(acc) ?: acc
            }
            seedsListToRanges().any { seedRange -> seed in seedRange }
        }
    }

    private fun seedsListToRanges(): Set<LongRange> {
        return seeds.chunked(2).map { it.first()..<it.first() + it.last() }.toSet()
    }

    companion object {
        fun fromGroups(input: List<String>): AlmanacAnalyzer {
            val seeds = input[0].split(": ").last().split(" ").map { it.toLong() }
            val mappings: List<Set<RangePair>> = input.drop(1)
                .map { group ->
                    group.split("\n")
                        .drop(1)
                        .map { RangePair.of(it) }
                        .toSet()
                }
            return AlmanacAnalyzer(seeds, mappings)
        }
    }
}