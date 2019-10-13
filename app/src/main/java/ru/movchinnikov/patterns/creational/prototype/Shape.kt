package ru.movchinnikov.patterns.creational.prototype

import java.util.*

abstract class Shape() {
    var x: Int = 0
    var y: Int = 0
    var color: String? = null

    constructor(target: Shape) : this() {
        x = target.x
        y = target.y
        color = target.color
    }

    abstract fun clone(): Shape

    override fun equals(other: Any?): Boolean {
        if (other !is Shape) return false
        return other.x == x && other.y == y && Objects.equals(other.color, color)
    }

    override fun hashCode(): Int {
        var result = x
        result = 31 * result + y
        result = 31 * result + color.hashCode()
        return result
    }
}