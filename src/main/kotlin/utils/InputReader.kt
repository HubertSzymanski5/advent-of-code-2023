package utils

import java.io.File

class InputReader(private val isTest: Boolean = false) {
    fun readFileLines(name: String) =
        File("src/${if (isTest) "test" else "main"}/resources/$name").useLines { it.toList() }

    fun readFileMap(name: String) = readFileLines(name)
        .flatMapIndexed { y, line ->
            line.mapIndexed { x, char -> Pair(Pair(x, y), char) }
        }.toMap()
}