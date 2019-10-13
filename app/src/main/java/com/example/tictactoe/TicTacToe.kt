package com.example.tictactoe

class TicTacToe {

    var isCircleTurn = false
    private val gameArray: Array<Array<GameEntity>>

    init {
        gameArray = arrayOf(
            arrayOf(GameEntity.EMPTY, GameEntity.EMPTY, GameEntity.EMPTY),
            arrayOf(GameEntity.EMPTY, GameEntity.EMPTY, GameEntity.EMPTY),
            arrayOf(GameEntity.EMPTY, GameEntity.EMPTY, GameEntity.EMPTY)
        )
    }

    fun nextTurn() {
        isCircleTurn = !isCircleTurn
    }

    fun updateCell(id: Int) {
        when (id) {
            R.id.first -> assignEntity(0, 0)
            R.id.second -> assignEntity(0, 1)
            R.id.third -> assignEntity(0, 2)
            R.id.fourth -> assignEntity(1, 0)
            R.id.fifth -> assignEntity(1, 1)
            R.id.sixth -> assignEntity(1, 2)
            R.id.seventh -> assignEntity(2, 0)
            R.id.eighth -> assignEntity(2, 1)
            R.id.ninth -> assignEntity(2, 2)
        }
    }

    fun checkWin(): GameResult {

        if (
            (gameArray[0][0] == GameEntity.CIRCLE && gameArray[0][1] == GameEntity.CIRCLE && gameArray[0][2] == GameEntity.CIRCLE) ||
            (gameArray[1][0] == GameEntity.CIRCLE && gameArray[1][1] == GameEntity.CIRCLE && gameArray[1][2] == GameEntity.CIRCLE) ||
            (gameArray[2][0] == GameEntity.CIRCLE && gameArray[2][1] == GameEntity.CIRCLE && gameArray[2][2] == GameEntity.CIRCLE) ||
            (gameArray[0][0] == GameEntity.CIRCLE && gameArray[1][1] == GameEntity.CIRCLE && gameArray[2][2] == GameEntity.CIRCLE) ||
            (gameArray[2][0] == GameEntity.CIRCLE && gameArray[1][1] == GameEntity.CIRCLE && gameArray[0][2] == GameEntity.CIRCLE) ||
            (gameArray[0][0] == GameEntity.CIRCLE && gameArray[1][0] == GameEntity.CIRCLE && gameArray[2][0] == GameEntity.CIRCLE) ||
            (gameArray[0][1] == GameEntity.CIRCLE && gameArray[1][1] == GameEntity.CIRCLE && gameArray[2][1] == GameEntity.CIRCLE) ||
            (gameArray[0][2] == GameEntity.CIRCLE && gameArray[1][2] == GameEntity.CIRCLE && gameArray[2][2] == GameEntity.CIRCLE)) {
            return GameResult.CIRCLE_WIN
        } else if (
            (gameArray[0][0] == GameEntity.CROSS && gameArray[0][1] == GameEntity.CROSS && gameArray[0][2] == GameEntity.CROSS) ||
            (gameArray[1][0] == GameEntity.CROSS && gameArray[1][1] == GameEntity.CROSS && gameArray[1][2] == GameEntity.CROSS) ||
            (gameArray[2][0] == GameEntity.CROSS && gameArray[2][1] == GameEntity.CROSS && gameArray[2][2] == GameEntity.CROSS) ||
            (gameArray[0][0] == GameEntity.CROSS && gameArray[1][1] == GameEntity.CROSS && gameArray[2][2] == GameEntity.CROSS) ||
            (gameArray[2][0] == GameEntity.CROSS && gameArray[1][1] == GameEntity.CROSS && gameArray[0][2] == GameEntity.CROSS) ||
            (gameArray[0][0] == GameEntity.CROSS && gameArray[1][0] == GameEntity.CROSS && gameArray[2][0] == GameEntity.CROSS) ||
            (gameArray[0][1] == GameEntity.CROSS && gameArray[1][1] == GameEntity.CROSS && gameArray[2][1] == GameEntity.CROSS) ||
            (gameArray[0][2] == GameEntity.CROSS && gameArray[1][2] == GameEntity.CROSS && gameArray[2][2] == GameEntity.CROSS)
        ) {
            return GameResult.CROSS_WIN
        }

        if (gameArray.any { line -> line.contains(GameEntity.EMPTY) })
            return GameResult.PLAY

        return GameResult.DRAW
    }

    private fun assignEntity(line: Int, column: Int) {
        gameArray[line][column] = if (isCircleTurn) GameEntity.CIRCLE else GameEntity.CROSS
    }

    enum class GameEntity {
        CROSS, CIRCLE, EMPTY
    }

    enum class GameResult {
        CROSS_WIN, CIRCLE_WIN, DRAW, PLAY
    }
}

