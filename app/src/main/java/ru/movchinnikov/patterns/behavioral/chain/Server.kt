package ru.movchinnikov.patterns.behavioral.chain

class Server {
    private val users = HashMap<String, String>()
    var middleware: Middleware? = null

    /**
     * Сервер получает email и пароль от клиента и запускает проверку
     * авторизации у цепочки.
     */
    fun logIn(email: String, password: String): Boolean {
        if (middleware!!.check(email, password)) {
            println("Authorization have been successful!")

            // Здесь должен быть какой-то полезный код, работающий для
            // авторизированных пользователей.

            return true
        }
        return false
    }

    fun register(email: String, password: String) {
        users[email] = password
    }

    fun hasEmail(email: String): Boolean {
        return users.containsKey(email)
    }

    fun isValidPassword(email: String, password: String): Boolean {
        return users[email] == password
    }
}