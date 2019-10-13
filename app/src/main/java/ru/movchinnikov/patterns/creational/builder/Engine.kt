package ru.movchinnikov.patterns.creational.builder

class Engine(var volume: Double, var mileage: Double) {
    var isStarted: Boolean = false

    fun on() {
        isStarted = true
    }

    fun off() {
        isStarted = false
    }

    fun go(mileage: Double) {
        if (isStarted) {
            this.mileage += mileage
        } else {
            System.err.println("Cannot go(), you must start engine first!")
        }
    }
}