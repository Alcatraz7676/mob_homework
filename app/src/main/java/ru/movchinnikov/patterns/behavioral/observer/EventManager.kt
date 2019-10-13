package ru.movchinnikov.patterns.behavioral.observer

import java.io.File

class EventManager {
    private var listeners: MutableMap<String, List<EventListener>> = HashMap()

    constructor(vararg operations: String) {
        for (operation in operations) {
            this.listeners[operation] = ArrayList()
        }
    }

    fun subscribe(eventType: String, listener: EventListener) {
        val users = listeners[eventType]?.toMutableList()
        users?.add(listener)
    }

    fun unsubscribe(eventType: String, listener: EventListener) {
        val users = listeners[eventType]?.toMutableList()
        users?.remove(listener)
    }

    fun notify(eventType: String, file: File) {
        val users = listeners[eventType]?.toMutableList()
        if (users != null) {
            for (listener in users) {
                listener.update(eventType, file)
            }
        }
    }
}