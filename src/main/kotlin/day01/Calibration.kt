package day01

class Calibration {

    private val spelledDigits = mapOf(
        "one" to "1",
        "two" to "2",
        "three" to "3",
        "four" to "4",
        "five" to "5",
        "six" to "6",
        "seven" to "7",
        "eight" to "8",
        "nine" to "9"
    )

    fun findCalibrationValues(document: List<String>): List<Int> {
        return document.map { findCalibrationValueIn(it) }
    }

    fun findCalibrationValuesWithSpelledDigits(document: List<String>): List<Int> {
        return findCalibrationValues(document.map { addSpelledDigits(it) })
    }

    private fun addSpelledDigits(line: String): String {
        var result = line
        // keep boundary letters as they can form another number
        spelledDigits.forEach {
            result = result.replace(it.key, "${it.key.first()}${it.value}${it.key.last()}")
        }
        return result
    }

    private fun findCalibrationValueIn(line: String): Int {
        return findFirstDigit(line) * 10 + findLastDigit(line)
    }

    private fun findFirstDigit(line: String): Int {
        return line.first { it in '0'..'9' }
            .digitToInt()
    }

    private fun findLastDigit(line: String): Int {
        return line.last { it in '0'..'9' }
            .digitToInt()
    }
}