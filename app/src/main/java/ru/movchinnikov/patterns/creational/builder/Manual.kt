package ru.movchinnikov.patterns.creational.builder

class Manual(var type: Type?, var seats: Int?, var engine: Engine?, var transmission: Transmission?,
             var tripComputer: TripComputer?, var gpsNavigator: GPSNavigator?) {

    fun print(): String {
        var info = ""
        info += "Type of car: $type\n"
        info += "Count of seats: $seats\n"
        info += "Engine: volume - " + engine?.volume + "; mileage - " + engine?.mileage + "\n"
        info += "Transmission: $transmission\n"
        info += if (tripComputer != null) {
            "Trip Computer: Functional" + "\n"
        } else {
            "Trip Computer: N/A" + "\n"
        }
        info += if (gpsNavigator != null) {
            "GPS Navigator: Functional" + "\n"
        } else {
            "GPS Navigator: N/A" + "\n"
        }
        return info
    }
}