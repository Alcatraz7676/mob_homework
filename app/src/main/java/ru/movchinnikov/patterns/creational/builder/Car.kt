package ru.movchinnikov.patterns.creational.builder

class Car(var type: Type?, var seats: Int?, var engine: Engine?, var transmission: Transmission?,
          var tripComputer: TripComputer?, var gpsNavigator: GPSNavigator?) {

    init {
        tripComputer?.car = this
    }

    var fuel = 0.0
}