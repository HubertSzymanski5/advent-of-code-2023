package day08

data class Graph(val nodes: Map<String, Pair<String, String>>) {

    fun goLeftFrom(currentNode: String): String {
        return nodes[currentNode]?.first ?: throw IllegalStateException("Node $currentNode doesn't exist")
    }

    fun goRightFrom(currentNode: String): String {
        return nodes[currentNode]?.second ?: throw IllegalStateException("Node $currentNode doesn't exist")
    }

    companion object {
        private val valueRegexp = """[A-Z0-9]{3}""".toRegex()

        fun from(input: String): Graph {
            val nodes = input.split("\n")
                .filter { it.isNotEmpty() }
                .associate { it.toEntry() }
            return Graph(nodes)
        }

        private fun String.toEntry(): Pair<String, Pair<String, String>> {
            val (key, left, right) = valueRegexp
                .findAll(this)
                .map { it.value }
                .filter { it.isNotEmpty() }
                .toList()

            return key to (left to right)
        }
    }
}
