package day04

import kotlin.math.pow

class ScratchCard(private val winningNumbers: Set<Int>, private val givenNumbers: Set<Int>) {

    fun wonCopies() = givenNumbers.intersect(winningNumbers).size

    fun cardPoints(): Int {
        val givenWinningCount = wonCopies()
        return if (givenWinningCount != 0)
            2.toDouble().pow(givenWinningCount - 1).toInt()
        else 0
    }

    companion object {
        fun fromString(line: String): ScratchCard {
            val (winningStr, givenStr) = line.split(":").last().trim().split("|")
            val winningNumbers = winningStr.extractNumbers()
            val givenNumbers = givenStr.extractNumbers()
            return ScratchCard(winningNumbers, givenNumbers)
        }

        private fun String.extractNumbers() = this.trim().split(Regex("( )+")).map { it.toInt() }.toSet()
    }
}