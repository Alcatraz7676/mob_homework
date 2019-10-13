package ru.movchinnikov.patterns.creational.prototype

class Rectangle() : Shape() {
    var width: Int = 0
    var height: Int = 0

    constructor(target: Rectangle) : this() {
        width = target.width
        height = target.height
    }

    override fun clone(): Shape {
        return Rectangle(this)
    }

    override fun equals(other: Any?): Boolean {
        if (other !is Rectangle || !super.equals(other)) return false
        return other.width == width && other.height == height
    }

    override fun hashCode(): Int {
        var result = super.hashCode()
        result = 31 * result + width
        result = 31 * result + height
        return result
    }
}