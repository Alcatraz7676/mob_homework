package ru.movchinnikov.patterns.structural.bridge

interface Remote {
    fun power()

    fun volumeDown()

    fun volumeUp()

    fun channelDown()

    fun channelUp()
}