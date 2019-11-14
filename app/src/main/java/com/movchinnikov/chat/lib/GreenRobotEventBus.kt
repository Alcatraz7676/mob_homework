package com.movchinnikov.chat.lib

object GreenRobotEventBus : EventBus {
    private var eventBus: org.greenrobot.eventbus.EventBus =
        org.greenrobot.eventbus.EventBus.getDefault()

    override fun register(subscriber: Any) {
        eventBus.register(subscriber)
    }

    override fun unregister(subscriber: Any) {
        eventBus.unregister(subscriber)
    }

    override fun post(event: Any) {
        eventBus.post(event)
    }
}
