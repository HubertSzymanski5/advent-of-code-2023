package day12

data class ReportRow(val springs: String, val brokenGroups: List<Int>) {

    fun findAllPossibleCombinations(): Long {
        return findAllPossibleCombinations(springs, brokenGroups)
    }

    fun findAllUnfoldedPossibleCombinations(): Long {
        val unfoldedSprings = (0..<5).joinToString(separator = "?") { springs }
        val unfoldedGroups = (0..<5).flatMap { brokenGroups }
        return findAllPossibleCombinations(unfoldedSprings, unfoldedGroups)
    }

    private fun findAllPossibleCombinations(config: String, groups: List<Int>): Long {
        if (groups.isEmpty()) return if ("#" in config) 0 else 1
        if (config.isEmpty()) return 0

        return cache.getOrPut("$config:$groups") {
            var result = 0L
            if (config.first() in ".?")
                result += findAllPossibleCombinations(config.drop(1), groups)
            if (config.first() in "#?"
                && groups.first() <= config.length
                && "." !in config.take(groups.first())
                && (groups.first() == config.length || config[groups.first()] != '#')
            )
                result += findAllPossibleCombinations(config.drop(groups.first() + 1), groups.drop(1))
            result
        }
    }

    companion object {

        private val cache = mutableMapOf<String, Long>()

        fun of(input: String): ReportRow {
            val (springs, brokenGroupsStr) = input.split(" ")
            val brokenGroups = brokenGroupsStr.split(",")
                .map { it.toInt() }
            return ReportRow(springs, brokenGroups)
        }
    }
}