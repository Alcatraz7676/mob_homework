package ru.movchinnikov.patterns.structural.bridge

interface Device {
    val isEnabled: Boolean

    var volume: Int

    var channel: Int

    fun enable()

    fun disable()

    fun printStatus()
}