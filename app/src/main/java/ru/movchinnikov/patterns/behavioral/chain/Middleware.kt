package ru.movchinnikov.patterns.behavioral.chain

abstract class Middleware {
    private var next: Middleware? = null

    /**
     * Помогает строить цепь из объектов-проверок.
     */
    fun linkWith(next: Middleware): Middleware {
        this.next = next
        return next
    }

    /**
     * Подклассы реализуют в этом методе конкретные проверки.
     */
    abstract fun check(email: String, password: String): Boolean

    /**
     * Запускает проверку в следующем объекте или завершает проверку, если мы в
     * последнем элементе цепи.
     */
    protected fun checkNext(email: String, password: String): Boolean {
        return if (next == null) {
            true
        } else next!!.check(email, password)
    }
}