package ru.movchinnikov.patterns.behavioral.template_method

/**
 * Базовый класс социальной сети.
 */
abstract class Network(val userName: String, val password: String) {

    /**
     * Публикация данных в любой сети.
     */
    fun post(message: String): Boolean {
        // Проверка данных пользователя перед постом в соцсеть. Каждая сеть для
        // проверки использует разные методы.
        if (logIn(userName, password)) {
            // Отправка данных.
            val result = sendData(message.toByteArray())
            logOut()
            return result
        }
        return false
    }

    abstract fun logIn(userName: String, password: String): Boolean
    abstract fun sendData(data: ByteArray): Boolean
    abstract fun logOut()
}