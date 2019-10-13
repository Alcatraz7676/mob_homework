package ru.movchinnikov.patterns.creational.singleton

class Singleton(value: String) {
    var value: String? = value

    init {
        try {
            Thread.sleep(1000)
        } catch (ex: InterruptedException) {
            ex.printStackTrace()
        }
    }

    companion object {
        private var instance: Singleton? = null

        fun getInstance(value: String): Singleton {
            if (instance == null) {
                instance =
                    Singleton(value)
            }
            return instance!!
        }
    }
}