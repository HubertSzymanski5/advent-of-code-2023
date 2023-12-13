package day12

class HotSprings(private val report: List<ReportRow>) {

    fun findAllPossibleCombinations(): Long = report.sumOf { it.findAllPossibleCombinations() }

    fun findAllPossibleCombinationsUnfolded(): Long = report.sumOf { it.findAllUnfoldedPossibleCombinations() }

    companion object {
        fun from(input: List<String>): HotSprings {
            return HotSprings(input.map { ReportRow.of(it) })
        }
    }


}