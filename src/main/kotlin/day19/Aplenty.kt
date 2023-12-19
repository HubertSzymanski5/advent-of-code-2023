package day19

import java.util.*

class Aplenty private constructor(private val workflows: Map<String, Workflow>, private val parts: List<Part>) {

    fun collectiveRatingOfAccepted(): Long {
        return parts.filter { isAccepted(it) }
            .sumOf { it.ratingsSum() }
    }

    fun getNumberOfDistinctRatingsAccepted(): Long {
        val maxRange = 1..4000
        val startRange = PartRange(maxRange, maxRange, maxRange, maxRange)
        val toProcessQueue = LinkedList(listOf(startRange to "in"))
        val accepted = mutableSetOf<PartRange>()
        val rejected = mutableSetOf<PartRange>()
        while (toProcessQueue.isNotEmpty()) {
            val (partsRange, key) = toProcessQueue.poll()
            val processed = workflows[key]?.resolve(partsRange) ?: throw IllegalStateException("Invalid key")
            processed.forEach {
                when (it.second) {
                    "R" -> rejected.add(it.first)
                    "A" -> accepted.add(it.first)
                    else -> toProcessQueue.add(it)
                }
            }
        }
        return accepted.sumOf { it.combinations() }
    }

    private fun isAccepted(part: Part): Boolean {
        var key = "in"
        while (key !in setOf("A", "R")) {
            key = workflows[key]?.resolve(part) ?: throw IllegalStateException("Cannot find workflow with key '$key'")
        }
        return key == "A"
    }


    companion object {
        fun from(input: List<String>): Aplenty {
            val (workflowsStr, partsStr) = input
            val workflows = workflowsStr.split("\n")
                .associate { workflowStr ->
                    val splitIndex = workflowStr.indexOfFirst { it == '{' }
                    val key = workflowStr.take(splitIndex)
                    val description = workflowStr.drop(splitIndex)
                    key to Workflow.from(description)
                }
            val parts = partsStr.split("\n")
                .map { Part.from(it) }
            return Aplenty(workflows, parts)
        }
    }
}

