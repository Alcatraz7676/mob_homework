package ru.movchinnikov.patterns.structural.bridge

class TV : Device {
    override var isEnabled = false
    override var volume = 30
        set(volume) {
            field = when {
                volume > 100 -> 100
                volume < 0 -> 0
                else -> volume
            }
        }
    override var channel = 1

    override fun enable() {
        isEnabled = true
    }

    override fun disable() {
        isEnabled = false
    }

    override fun printStatus() {
        println("------------------------------------")
        println("| I'm TV set.")
        println("| I'm " + if (isEnabled) "enabled" else "disabled")
        println("| Current volume is " + this.volume + "%")
        println("| Current channel is $channel")
        println("------------------------------------\n")
    }
}