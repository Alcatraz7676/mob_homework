package ru.movchinnikov.patterns.structural.adapter

open class RoundPeg() {
    var mRadius: Double? = null

    constructor(radius: Double) : this() {
        this.mRadius = radius
    }

    open fun getRadius() = mRadius!!
}