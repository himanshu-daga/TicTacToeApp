package com.example.tictactoe

import android.graphics.Color
import android.os.Bundle
import android.os.Handler
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {
    var chance:Int = 0
    lateinit var buttons : List<Button>
    var visited = Array(9){-1}

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val b1:Button = findViewById(R.id.b1)
        val b2:Button = findViewById(R.id.b2)
        val b3:Button = findViewById(R.id.b3)
        val b4:Button = findViewById(R.id.b4)
        val b5:Button = findViewById(R.id.b5)
        val b6:Button = findViewById(R.id.b6)
        val b7:Button = findViewById(R.id.b7)
        val b8:Button = findViewById(R.id.b8)
        val b9:Button = findViewById(R.id.b9)
        val reset:ImageView = findViewById(R.id.resetGame)
        reset.setOnClickListener { reset() }

        buttons = listOf(b1,b2,b3,b4,b5,b6,b7,b8,b9)
        for(button in buttons)
            button.setOnClickListener { fillImg(button, chance) }

//        b1.setOnClickListener { fillImg(b1, chance) }
//        b2.setOnClickListener { fillImg(b2, chance) }
//        b3.setOnClickListener { fillImg(b3, chance) }
//        b4.setOnClickListener { fillImg(b4, chance) }
//        b5.setOnClickListener { fillImg(b5, chance) }
//        b6.setOnClickListener { fillImg(b6, chance) }
//        b7.setOnClickListener { fillImg(b7, chance) }
//        b8.setOnClickListener { fillImg(b8, chance) }
//        b9.setOnClickListener { fillImg(b9, chance) }

    }

    private fun fillImg(button: Button, chance: Int) {
        button.setBackgroundResource(when(chance%2) {
            0 -> R.drawable.tic_x
            else -> R.drawable.tic_o
        })
        button.isEnabled = false
        this.chance++
        checkGameWon(button)
    }

    private fun checkGameWon(button: Button) {

        val bId = buttons.indexOf(button)
        // when 'X' plays fill that index with 1 and
        // when 'O' plays fill that index with 0 in visited array
        val player = when(chance%2){
            0 -> 1
            else -> 0
        }
        visited[bId] = player
        // Toast.makeText(this, "Visited:" + visited.contentToString(),Toast.LENGTH_SHORT).show()

        if(chance<5)
            return

        // check if last move made player won
        when(bId){
            0-> if((visited[0] == visited[1] && visited[0]==visited[2]) ||
                (visited[0] == visited[3] && visited[0]==visited[6]) ||
                (visited[0] == visited[4] && visited[0]==visited[8]))
                    endGame(player)
            1-> if((visited[1] == visited[0] && visited[1]==visited[2]) ||
                (visited[1] == visited[4] && visited[1]==visited[7]))
                    endGame(player)
            2-> if((visited[2] == visited[0] && visited[2]==visited[1]) ||
                (visited[2] == visited[6] && visited[2]==visited[4]) ||
                (visited[2] == visited[5] && visited[2]==visited[8]))
                endGame(player)
            3-> if((visited[3] == visited[0] && visited[3]==visited[6]) ||
                (visited[3] == visited[4] && visited[3]==visited[5]))
                endGame(player)
            4-> if((visited[4] == visited[0] && visited[4]==visited[8]) ||
                (visited[4] == visited[1] && visited[4]==visited[7]) ||
                (visited[4] == visited[2] && visited[4]==visited[6]) ||
                (visited[4] == visited[5] && visited[4]==visited[3]))
                endGame(player)
            5-> if((visited[5] == visited[2] && visited[5]==visited[8]) ||
                (visited[5] == visited[3] && visited[5]==visited[4]))
                endGame(player)
            6-> if((visited[6] == visited[0] && visited[6]==visited[3]) ||
                (visited[6] == visited[4] && visited[6]==visited[2]) ||
                (visited[6] == visited[7] && visited[6]==visited[8]))
                endGame(player)
            7-> if((visited[7] == visited[1] && visited[7]==visited[4]) ||
                (visited[7] == visited[6] && visited[7]==visited[8]))
                endGame(player)
            else-> if((visited[8] == visited[6] && visited[8]==visited[7]) ||
                (visited[8] == visited[0] && visited[8]==visited[4]) ||
                (visited[8] == visited[2] && visited[8]==visited[5]))
                endGame(player)
        }

        if(chance>8)
            endGame(-1)
    }

    private fun endGame(state: Int) {
        when(state){
            0-> Toast.makeText(this, "X won!",Toast.LENGTH_LONG).show()
            1-> Toast.makeText(this, "O won!",Toast.LENGTH_LONG).show()
            else -> Toast.makeText(this, "Game Over!",Toast.LENGTH_LONG).show()
        }
        reset()
    }

    private fun reset() {
        chance = 0
        for( i in 0..8) visited[i] = -1
        for(b in buttons) {
            b.isEnabled = true
            b.setBackgroundColor(Color.parseColor("#35495b"))
        }
    }
}

//BG 35495b and 2a3e50
//O and X:  #3cc6f4 and #ff6160