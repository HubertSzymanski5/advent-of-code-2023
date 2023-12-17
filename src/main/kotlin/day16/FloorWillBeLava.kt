package day16

private typealias Direction = utils.Commons.Direction

class FloorWillBeLava(private val contraption: Map<Pair<Int, Int>, Char>) {

    fun getNumOfEnergizedTiles(start: Photon = Photon(0 to 0, Direction.E)): Int {
        val energizedTiles: MutableMap<Pair<Int, Int>, Direction?> =
            contraption.keys.associateWith { null }.toMutableMap()
        startBeam(energizedTiles, start)
        return energizedTiles.filter { it.value != null }.count()
    }

    fun getMaxNumOfEnergizedTiles(): Int {
        val maxX = contraption.keys.maxOf { it.first }
        val maxY = contraption.keys.maxOf { it.second }
        val ins = (0..maxX).flatMap { x ->
            listOf(
                Photon((x to 0), Direction.S),
                Photon((x to maxY), Direction.N)
            )
        } + (0..maxY).flatMap { y ->
            listOf(
                Photon((0 to y), Direction.E),
                Photon(maxX to y, Direction.W)
            )
        }
        return ins.maxOf { getNumOfEnergizedTiles(it) }
    }

    private fun startBeam(energizedTiles: MutableMap<Pair<Int, Int>, Direction?>, beam: Photon) {
        while (energizedTiles[beam.position] != beam.direction) {
            val tile = contraption[beam.position] ?: return
            energizedTiles[beam.position] = beam.direction
            when (tile) {
                '.' -> {}
                '/' -> when (beam.direction) {
                    Direction.N -> beam.direction = Direction.E
                    Direction.S -> beam.direction = Direction.W
                    Direction.W -> beam.direction = Direction.S
                    Direction.E -> beam.direction = Direction.N
                    else -> throw IllegalStateException("Illegal direction")
                }

                '\\' -> when (beam.direction) {
                    Direction.N -> beam.direction = Direction.W
                    Direction.S -> beam.direction = Direction.E
                    Direction.W -> beam.direction = Direction.N
                    Direction.E -> beam.direction = Direction.S
                    else -> throw IllegalStateException("Illegal direction")
                }

                '|' -> if (beam.direction in setOf(Direction.W, Direction.E)) {
                    startBeam(energizedTiles, Photon(beam.position + Direction.N.cord, Direction.N))
                    startBeam(energizedTiles, Photon(beam.position + Direction.S.cord, Direction.S))
                    return
                }

                '-' -> if (beam.direction in setOf(Direction.N, Direction.S)) {
                    startBeam(energizedTiles, Photon(beam.position + Direction.E.cord, Direction.E))
                    startBeam(energizedTiles, Photon(beam.position + Direction.W.cord, Direction.W))
                    return
                }
            }
            beam.moveOneStep()
        }
    }

    private operator fun Pair<Int, Int>.plus(other: Pair<Int, Int>): Pair<Int, Int> {
        return this.first + other.first to this.second + other.second
    }

    data class Photon(var position: Pair<Int, Int>, var direction: Direction) {

        fun moveOneStep() {
            position = position.first + direction.cord.first to position.second + direction.cord.second
        }

    }
}