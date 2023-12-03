package day01

import org.junit.jupiter.api.Assertions.assertEquals
import utils.InputReader
import kotlin.test.Test

class CalibrationTest {

    private val inputReader = InputReader(true)
    private val calibration = Calibration()

    @Test
    fun shouldPassForOneLineWithOneDigit() {
        val result = calibration.findCalibrationValues(listOf("ighrabdjf5igjhudsab"))
        assertEquals(listOf(55), result)
    }

    @Test
    fun shouldPassForOneLineWithTwoDigits() {
        val result = calibration.findCalibrationValues(listOf("igh2abdjfigjhud7sab"))
        assertEquals(listOf(27), result)
    }

    @Test
    fun shouldPassForOneLineWithMultipleDigits() {
        val result = calibration.findCalibrationValues(listOf("igh3ab42fig3214512hud1sab"))
        assertEquals(listOf(31), result)
    }

    @Test
    fun shouldPassForOneSpelledDigit() {
        val result = calibration.findCalibrationValuesWithSpelledDigits(listOf("asdonedw"))
        assertEquals(listOf(11), result)
    }

    @Test
    fun shouldPassForMixedSpelledAndDigit() {
        val result = calibration.findCalibrationValuesWithSpelledDigits(listOf("asnine23sdw"))
        assertEquals(listOf(93), result)
    }

    @Test
    fun shouldPassForMultipleSpelled() {
        val result = calibration.findCalibrationValuesWithSpelledDigits(listOf("eightwothree"))
        assertEquals(listOf(83), result)
    }

    @Test
    fun shouldPassExampleInputPartI() {
        val testInput = inputReader.readFileLines("day01")
        val result = calibration.findCalibrationValues(testInput)
        assertEquals(listOf(12, 38, 15, 77), result)
    }

    @Test
    fun shouldPassExampleInputPartII() {
        val testInput = inputReader.readFileLines("day01-part2")
        val result = calibration.findCalibrationValuesWithSpelledDigits(testInput)
        assertEquals(listOf(29, 83, 13, 24, 42, 14, 76), result)
    }
}