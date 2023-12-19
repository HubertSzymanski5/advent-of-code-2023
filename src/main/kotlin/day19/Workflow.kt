package day19

class Workflow private constructor(
    private val rules: List<Rule>,
    private val default: String
) {
    fun resolve(part: Part): String {
        return rules.firstOrNull {
            when (it.compareSign) {
                '>' -> part.fieldFrom(it.field) > it.value
                '<' -> part.fieldFrom(it.field) < it.value
                else -> throw IllegalArgumentException("Invalid operation '${it.compareSign}'")
            }
        }?.result ?: default
    }

    fun resolve(partRange: PartRange): List<Pair<PartRange, String>> {
        val result = mutableListOf<Pair<PartRange, String>>()
        var toFilter = partRange
        for (rule in rules) {
            val (matched, left) = rule.dividePartRange(toFilter)
            if (matched != null) {
                result.add(matched to rule.result)
            }
            if (left == null)
                return result
            toFilter = left
        }
        result.add(toFilter to default)
        return result
    }


    /**
     * returns pair where first matches the rule and second does not need further processing
     */
    private fun Rule.dividePartRange(partRange: PartRange): Pair<PartRange?, PartRange?> {
        val range = partRange.fieldFrom(field)
        return when {
            value in range -> partRange.splitOn(field, value, compareSign)
            (range.first > value && compareSign == '>') || (range.last < value && compareSign == '<') -> partRange to null
            else -> null to partRange
        }
    }

    private fun PartRange.splitOn(field: Char, value: Int, sign: Char): Pair<PartRange, PartRange> {
        val range = fieldFrom(field)
        val (matched, rest) = when (sign) {
            '>' -> value + 1..range.last to range.first..value
            '<' -> range.first..<value to value..range.last
            else -> throw IllegalArgumentException()
        }
        return this.copyWithReplace(field, matched) to this.copyWithReplace(field, rest)
    }

    companion object {
        fun from(input: String): Workflow {
            val inputs = input.drop(1).dropLast(1).split(",")
            val default = inputs.last()
            val rules = inputs.dropLast(1)
                .map {
                    val (condition, result) = it.split(":")
                    val field = condition[0]
                    val compareSign = condition[1]
                    val value = condition.drop(2).toInt()
                    Rule(field, compareSign, value, result)
                }
            return Workflow(rules, default)
        }
    }
}

data class Rule(val field: Char, val compareSign: Char, val value: Int, val result: String)
