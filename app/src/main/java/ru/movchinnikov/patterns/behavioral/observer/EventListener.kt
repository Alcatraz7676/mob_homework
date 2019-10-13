package ru.movchinnikov.patterns.behavioral.observer

import java.io.File

interface EventListener {
    fun update(eventType: String, file: File)
}