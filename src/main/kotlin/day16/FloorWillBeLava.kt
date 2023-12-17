package day16

import utils.Commons

typealias Direction = utils.Commons.Direction

class FloorWillBeLava(private val contraption: Map<Pair<Int, Int>, Char>) {

    private val energizedTiles: Map<Pair<Int, Int>, Direction?> = contraption.keys.associateWith { null }

    fun getNumOfEnergizedTiles(): Int {
        insertStartBeam()
        return energizedTiles.filter { it.value == null }.count()
    }

    private fun insertStartBeam() {
        val currentBeam = Photon(0 to 0, Direction.E)
        while (energizedTiles[currentBeam.position] != currentBeam.direction) {
            val tileType = contraption[currentBeam.position]!! // todo: handle going of the grid!
            when (tileType) {
                '.' -> currentBeam.position += currentBeam.direction.cord
                '/' -> TODO()
                '\\' -> TODO("redirect depending on direction")
                '|' -> TODO()
                '-' -> TODO("split beams into two if hit flat")
            }
            println("${currentBeam.position} : ${currentBeam.direction}")
        }
    }

    private operator fun Pair<Int, Int>.plus(other: Pair<Int, Int>): Pair<Int, Int> {
        return this.first + other.first to this.second + other.second
    }

    data class Photon(var position: Pair<Int, Int>, var direction: Direction)
}