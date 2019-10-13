package ru.movchinnikov.patterns.structural.bridge

class AdvancedRemote(device: Device) : BasicRemote(device) {

    fun mute() {
        println("Remote: mute")
        device.volume = 0
    }
}