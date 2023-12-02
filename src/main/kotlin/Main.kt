import day01.Calibration
import day02.CubeGameValidator
import utils.InputReader

val inputReader = InputReader()

fun main() {
    day02()
}

fun day02() {
    val input = inputReader.readFile("day02")
    val gameValidator = CubeGameValidator()
    printResults(
        "02",
        gameValidator.findPossibleGames(input).sum(),
        gameValidator.findPowersOfFewestNumberForEachGame(input).sum()
    )
}

fun day01() {
    val input = inputReader.readFile("day01")
    val calibration = Calibration()
    printResults(
        "01",
        calibration.findCalibrationValues(input).sum(),
        calibration.findCalibrationValuesWithSpelledDigits(input).sum()
    )
}

fun printResults(day: String, partI: Any = "", partII: Any = "") {
    println("Day$day:")
    println("\tpart   I: $partI")
    println("\tpart  II: $partII")
    println("---------------------------------------------------------")
}