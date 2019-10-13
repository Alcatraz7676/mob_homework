package com.example.tictactoe

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.TextView
import androidx.constraintlayout.widget.Group
import androidx.core.content.ContextCompat
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private lateinit var ticTacToe: TicTacToe

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        ticTacToe = TicTacToe()

        cells.setAllOnClickListener(View.OnClickListener {
                view -> cellClick(view as TextView)
        })

        resetBtn.setOnClickListener {
            ticTacToe = TicTacToe()
            updateTurnTextView(TicTacToe.GameResult.PLAY)
            cells.enableClicks()
        }
    }

    private fun cellClick(btnView: TextView) {
        if (ticTacToe.isCircleTurn)
            btnView.text = "O"
        else
            btnView.text = "X"
        btnView.isClickable = false
        btnView.isFocusable = false
        ticTacToe.updateCell(btnView.id)
        ticTacToe.nextTurn()
        val winner = ticTacToe.checkWin()
        updateTurnTextView(winner)
    }

    private fun updateTurnTextView(winner: TicTacToe.GameResult) {
        when (winner) {
            TicTacToe.GameResult.CIRCLE_WIN -> {
                turnTextView.text = "Победили нолики"
                turnTextView.setTextViewColor(this, R.color.colorAccent)
                cells.disableClicks()
            }
            TicTacToe.GameResult.CROSS_WIN -> {
                turnTextView.text = "Победили крестики"
                turnTextView.setTextViewColor(this, R.color.colorAccent)
                cells.disableClicks()
            }
            TicTacToe.GameResult.DRAW -> {
                turnTextView.text = "Ничья"
                turnTextView.setTextViewColor(this, R.color.colorAccent)
            }
            TicTacToe.GameResult.PLAY -> {
                turnTextView.text = if (ticTacToe.isCircleTurn) "Ход ноликов" else "Ход крестиков"
                turnTextView.setTextViewColor(this, R.color.textColorPrimaryDark)
            }
        }
    }

    private fun TextView.setTextViewColor(context: Context, colorId: Int) {
        this.setTextColor(ContextCompat.getColor(context, colorId))
    }

    private fun Group.setAllOnClickListener(listener: View.OnClickListener) {
        referencedIds.forEach { id ->
            rootView.findViewById<View>(id).setOnClickListener(listener)
        }
    }

    private fun Group.enableClicks() {
        referencedIds.forEach { id ->
            val btnView = rootView.findViewById<TextView>(id)
            btnView.text = ""
            btnView.isClickable = true
            btnView.isFocusable = true
        }
    }

    private fun Group.disableClicks() {
        referencedIds.forEach { id ->
            val btnView = rootView.findViewById<TextView>(id)
            btnView.isClickable = false
            btnView.isFocusable = false
        }
    }

}
