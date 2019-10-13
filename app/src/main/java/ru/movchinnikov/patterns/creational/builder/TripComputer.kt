package ru.movchinnikov.patterns.creational.builder

class TripComputer {
    var car: Car? = null

    fun showFuelLevel() {
        println("Fuel level: ${car?.fuel}")
    }

    fun showStatus() {
        if (car?.engine != null && car?.engine!!.isStarted) {
            println("Car is started")
        } else {
            println("Car isn't started")
        }
    }
}