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
import day11.CosmicExpansion
import day12.HotSprings
import day13.PointOfIncidence
import day14.ParabolicReflectorDish
import day15.LensLibrary
import day16.FloorWillBeLava
import day17.ClumsyCrucible
import day18.LavaDuctLagoon
import utils.InputReader

val inputReader = InputReader()

fun main() {
    day18()
}

fun day18() {
    val input = inputReader.readFileLines("day18")
    val lavaDuctLagoon = LavaDuctLagoon.from(input)
    printResults(
        "18",
        lavaDuctLagoon.calculateVolume(),
        lavaDuctLagoon.calculateCorrectedVolume()
    )
}

fun day17() {
    val input = inputReader.readFileMap("day17")
    val clumsyCrucible = ClumsyCrucible.from(input)
    printResults(
        "17",
        clumsyCrucible.findMinimalHeatLoss(),
        clumsyCrucible.findMinimalHeatLossForUltraCrucibles()
    )
}

fun day16() {
    val input = inputReader.readFileMap("day16")
    val floorWillBeLava = FloorWillBeLava(input)
    printResults(
        "16",
        floorWillBeLava.getNumOfEnergizedTiles(),
        floorWillBeLava.getMaxNumOfEnergizedTiles()
    )
}

fun day15() {
    val input = inputReader.readFileLines("day15").first()
    val lensLibrary = LensLibrary.of(input)
    printResults(
        "15",
        lensLibrary.hashSum(),
        lensLibrary.calculateFocusingPower()
    )
}

fun day14() {
    val input = inputReader.readFileLines("day14")
    val parabolicReflectorDish = ParabolicReflectorDish(input)
    printResults(
        "14",
        parabolicReflectorDish.calculateTotalLoad(),
        parabolicReflectorDish.calculateTotalLoadAfterSpins()
    )
}

fun day13() {
    val input = inputReader.readFileGroups("day13")
    val pointOfIncidence = PointOfIncidence.from(input)
    printResults(
        "13",
        pointOfIncidence.findMirrorsNumber(),
        pointOfIncidence.findMirrorsNumber(1)
    )
}

fun day12() {
    val input = inputReader.readFileLines("day12")
    val hotSprings = HotSprings.from(input)
    printResults(
        "12",
        hotSprings.findAllPossibleCombinations(),
        hotSprings.findAllPossibleCombinationsUnfolded()
    )
}

fun day11() {
    val input = inputReader.readFileMap("day11")
    val cosmicExpansion = CosmicExpansion(input)
    printResults(
        "11",
        cosmicExpansion.findDistancesBetweenGalaxies().sum(),
        cosmicExpansion.findDistancesBetweenGalaxies(1_000_000).sum()
    )
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
        "03", engineSchematicAnalyzer.findPartNumbersSum(input), engineSchematicAnalyzer.findGearRatiosSum(input)
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