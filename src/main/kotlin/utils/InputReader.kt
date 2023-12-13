package utils

import java.io.File

class InputReader(private val isTest: Boolean = false) {
    fun readFileLines(name: String) = getFile(name).useLines { it.toList() }

    fun readFileMap(name: String) = readFileLines(name)
        .flatMapIndexed { y, line ->
            line.mapIndexed { x, char -> Pair(Pair(x, y), char) }
        }.toMap()

    fun readFileGroups(name: String) = getFile(name).readText().split("\n\n").map { it.trim() }

    private fun getFile(name: String) = File("src/${if (isTest) "test" else "main"}/resources/$name")
}