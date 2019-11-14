package com.movchinnikov.chat.lib

interface EventBus {
    fun register(subscriber: Any)
    fun unregister(subscriber: Any)
    fun post(event: Any)
}
