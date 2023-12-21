package day20

import utils.Commons
import java.util.*

class PulsePropagation private constructor(private val modules: Map<String, Module>) {

    private var highSignals: Long = 0L
    private var lowSignals: Long = 0L
    private val observed: List<Module> by lazy {
        val rxInputModule = modules["tg"] ?: throw IllegalStateException("RX module doesnt exist")
        rxInputModule.inputs.map { modules[it]!! }
    }

    fun getMultiplyOfSignals(): Long {
        (1..1000).forEach { pressButton() }
        return highSignals * lowSignals
    }

    fun getNumberOfPressesToTurnOnRx(): Long {
        modules.values.forEach { it.reset() }
        var count = 0L
        val cycles = mutableMapOf<Module, Long?>()
        do {
            count++
            pressButton()
            observed
                .forEach {
                    if (it.lastSignal == Signal.HIGH) {
                        cycles[it] = count
                        println(cycles)
                    }
                }
        } while (cycles.size != observed.size)

        println(cycles)

        return cycles.values.map { it!! }.reduce { acc, num -> Commons.lcm(acc, num) }
    }

    private fun pressButton() {
        val toProcessQueue = LinkedList(listOf(Triple("button", listOf("broadcaster"), Signal.LOW)))
        while (toProcessQueue.isNotEmpty()) {
            val (source, modulesToSendTo, signal) = toProcessQueue.poll()
            if (signal == Signal.HIGH) highSignals += modulesToSendTo.size else lowSignals += modulesToSendTo.size
            val newToProcess = modulesToSendTo.mapNotNull { modules[it]?.processSignal(signal, source) }
            toProcessQueue.addAll(newToProcess)
        }
    }

    companion object {
        fun from(input: List<String>): PulsePropagation {
            val map = mutableMapOf<String, MutableList<String>>()
            val modules = input.map {
                val (module, outputStr) = it.split(" -> ")
                val (key, type) = when {
                    module.startsWith("%") -> module.drop(1) to ModuleType.FLIP_FLOP
                    module.startsWith("&") -> module.drop(1) to ModuleType.CONJUNCTION
                    "broadcaster" == module -> module to ModuleType.BROADCAST
                    "output" == module -> module to ModuleType.OUTPUT
                    else -> throw IllegalArgumentException("Cannot map module $module")
                }
                val outputs = outputStr.split(", ")
                outputs.forEach { output ->
                    if (map.containsKey(output)) map[output]!!.add(key)
                    else map[output] = mutableListOf(key)
                }
                Triple(key, type, outputs)
            }
                .map { Module.of(it.first, it.second, map[it.first] ?: emptyList(), it.third) }
                .associateBy { it.name }
            return PulsePropagation(modules)
        }
    }
}

enum class Signal {
    LOW, HIGH
}

enum class ModuleType {
    BROADCAST, FLIP_FLOP, CONJUNCTION, OUTPUT
}