package day02

class CubeGameValidator {

    private val MAX_RED = 12
    private val MAX_GREEN = 13
    private val MAX_BLUE = 14

    fun findPossibleGames(gamesLog: List<String>): List<Int> {
        return gamesLog.map(GameRecord::fromString)
            .filter(this::isPossibleGame)
            .map { it.gameId }
    }

    fun findPowersOfFewestNumberForEachGame(gamesLog: List<String>): List<Int> =
        findFewestNumberForEachGame(gamesLog).mapToPowerOfCubes()

    fun findFewestNumberForEachGame(gamesLog: List<String>): List<Triple<Int, Int, Int>> {
        return gamesLog.map(GameRecord::fromString)
            .map { findFewestNumberOfCubesFor(it) }
    }

    private fun List<Triple<Int, Int, Int>>.mapToPowerOfCubes(): List<Int> =
        this.map { it.first * it.second * it.third }

    private fun findFewestNumberOfCubesFor(game: GameRecord): Triple<Int, Int, Int> {
        var (minRed, minGreen, minBlue) = Triple(0, 0, 0)
        game.reveals.forEach { reveal ->
            if (minRed < reveal.red) minRed = reveal.red
            if (minGreen < reveal.green) minGreen = reveal.green
            if (minBlue < reveal.blue) minBlue = reveal.blue
        }
        return Triple(minRed, minGreen, minBlue)
    }

    private fun isPossibleGame(gameRecord: GameRecord): Boolean {
        return gameRecord.reveals.any {
            it.red > MAX_RED || it.green > MAX_GREEN || it.blue > MAX_BLUE
        }.not()
    }

    private class GameRecord(val gameId: Int, val reveals: List<CubesReveal>) {
        companion object {
            fun fromString(gameLog: String): GameRecord {
                val (gameStr, revealsStr) = gameLog.split(": ")
                val gameId = gameStr.split(" ").last().toInt()
                val reveals = revealsStr.split("; ").map { CubesReveal.fromString(it) }
                return GameRecord(gameId = gameId, reveals = reveals)
            }
        }

        override fun toString(): String {
            return "gameId: $gameId; reveals = $reveals"
        }
    }

    private class CubesReveal(val red: Int = 0, val green: Int = 0, val blue: Int = 0) {
        companion object {
            fun fromString(revealLog: String): CubesReveal {
                var (r, g, b) = listOf(0, 0, 0)
                val revealsStrings = revealLog.split(", ")
                revealsStrings.forEach { str ->
                    val (numStr, color) = str.split(" ")
                    val num = numStr.toInt()
                    when (color) {
                        "red" -> r = num
                        "green" -> g = num
                        "blue" -> b = num
                        else -> throw IllegalArgumentException("Unknown color: $color")
                    }
                }
                return CubesReveal(r, g, b)
            }
        }

        override fun toString(): String {
            return "{r: $red, g: $green, b: $blue}"
        }
    }
}