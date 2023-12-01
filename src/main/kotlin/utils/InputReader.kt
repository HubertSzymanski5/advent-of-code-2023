package utils

import java.io.File

class InputReader(private val isTest: Boolean = false) {
    fun readFile(name: String) = File("src/${if (isTest) "test" else "main"}/resources/$name").useLines { it.toList() }
}