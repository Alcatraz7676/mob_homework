package ru.movchinnikov.patterns.creational.prototype

class Circle() : Shape() {
    var radius: Int = 0

    constructor(target: Circle) : this() {
        radius = target.radius
    }

    override fun clone(): Shape {
        return Circle(this)
    }

    override fun equals(other: Any?): Boolean {
        if (other !is Circle || !super.equals(other)) return false
        return other.radius == radius
    }

    override fun hashCode(): Int {
        var result = super.hashCode()
        result = 31 * result + radius
        return result
    }
}