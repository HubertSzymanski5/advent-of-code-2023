import day01.Calibration
import day02.CubeGameValidator
import day03.EngineSchematicAnalyzer
import day04.ScratchCardChecker
import day05.AlmanacAnalyzer
import day06.BoatRace
import day07.CamelCards
import day08.DesertNavigator
import day09.InstabilitySensor
import day10.PipeMaze
import utils.InputReader

val inputReader = InputReader()

fun main() {
    day10()
}

fun day10() {
    val input = inputReader.readFileMap("day10")
    val pipeMaze = PipeMaze(input)
    printResults(
        "10",
        pipeMaze.getStepsAlongTheLoopToAnimal(),
        pipeMaze.getLoopArea()
    )
}

fun day09() {
    val input = inputReader.readFileLines("day09")
    val instabilitySensor = InstabilitySensor.from(input)
    printResults(
        "09",
        instabilitySensor.findExtrapolatedValues().sum(),
        instabilitySensor.findExtrapolatedBackwardsValues().sum()
    )
}

fun day08() {
    val input = inputReader.readFileGroups("day08")
    val desertNavi = DesertNavigator.from(input)
    printResults(
        "08",
        desertNavi.findNumberOfSteps(),
        desertNavi.findNumberOfStepsForGhosts()
    )
}

fun day07() {
    val input = inputReader.readFileLines("day07")
    val camelCards = CamelCards.from(input)
    printResults(
        "07",
        camelCards.calculateTotalWinnings(),
        camelCards.calculateTotalWinningsWithJokers()
    )
}

fun day06() {
    val input = inputReader.readFileLines("day06")
    val boatRace = BoatRace.from(input)
    printResults(
        "06",
        boatRace.calculateErrorMargin(),
        boatRace.calculateErrorMarginForOneRace()
    )
}

fun day05() {
    val input = inputReader.readFileGroups("day05")
    val almanacAnalyzer = AlmanacAnalyzer.fromGroups(input)
    printResults(
        "05",
        almanacAnalyzer.getLowestLocationOfGivenSeeds(),
        almanacAnalyzer.getLowestLocationOfGivenRangesOfSeeds()
    )
}

fun day04() {
    val input = inputReader.readFileLines("day04")
    val scratchCardChecker = ScratchCardChecker()
    printResults(
        "04",
        scratchCardChecker.calculateTotalPointsFromCards(input),
        scratchCardChecker.calculateTotalScratchCards(input)
    )
}

fun day03() {
    val input = inputReader.readFileMap("day03")
    val engineSchematicAnalyzer = EngineSchematicAnalyzer()
    printResults(
        "03",
        engineSchematicAnalyzer.findPartNumbersSum(input),
        engineSchematicAnalyzer.findGearRatiosSum(input)
    )
}

fun day02() {
    val input = inputReader.readFileLines("day02")
    val gameValidator = CubeGameValidator()
    printResults(
        "02",
        gameValidator.findPossibleGames(input).sum(),
        gameValidator.findPowersOfFewestNumberForEachGame(input).sum()
    )
}

fun day01() {
    val input = inputReader.readFileLines("day01")
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