package day20

interface Module {
    val name: String
    val inputs: List<String>
    var lastSignal: Signal?

    fun processSignal(signal: Signal, source: String): Triple<String, List<String>, Signal>?
    fun reset()

    companion object {
        fun of(name: String, type: ModuleType, inputs: List<String>, outputs: List<String>): Module =
            when (type) {
                ModuleType.BROADCAST -> Broadcast(name, outputs)
                ModuleType.FLIP_FLOP -> FlipFlop(name, outputs)
                ModuleType.CONJUNCTION -> Conjunction(name, inputs, outputs)
                ModuleType.OUTPUT -> Output(name)
            }
    }
}

class Broadcast(override val name: String, private val outputs: List<String>) : Module {
    override val inputs: List<String> = emptyList()
    override var lastSignal: Signal? = null

    override fun processSignal(signal: Signal, source: String): Triple<String, List<String>, Signal> {
        lastSignal = signal
        return Triple(name, outputs, signal)
    }

    override fun reset() {} // do nothing
}

class FlipFlop(override val name: String, private val outputs: List<String>) : Module {
    override val inputs: List<String> = emptyList()
    private var isOn = false
    override var lastSignal: Signal? = null

    override fun processSignal(signal: Signal, source: String): Triple<String, List<String>, Signal>? {
        if (signal == Signal.HIGH)
            return null // nothing happens - sends to no one
        isOn = !isOn
        return if (isOn) {
            lastSignal = Signal.HIGH
            Triple(name, outputs, Signal.HIGH)
        } else {
            lastSignal = Signal.LOW
            Triple(name, outputs, Signal.LOW)
        }
    }

    override fun reset() {
        isOn = false
    }
}

class Conjunction(override val name: String, override val inputs: List<String>, private val outputs: List<String>) :
    Module {
    private val memory = inputs.associateWith { Signal.LOW }.toMutableMap()
    override var lastSignal: Signal? = null

    override fun processSignal(signal: Signal, source: String): Triple<String, List<String>, Signal> {
        memory[source] = signal
        if (memory.all { it.value == Signal.HIGH }) {
            lastSignal = Signal.LOW
            return Triple(name, outputs, Signal.LOW)
        } else {
            lastSignal = Signal.HIGH
            return Triple(name, outputs, Signal.HIGH)
        }
    }

    override fun reset() {
        memory.keys.forEach { memory[it] = Signal.LOW }
    }
}

class Output(override val name: String) : Module {
    override var lastSignal: Signal? = null
    override val inputs: List<String> = emptyList()
    override fun processSignal(signal: Signal, source: String): Triple<String, List<String>, Signal>? = null
    override fun reset() {} // do nothing
}
