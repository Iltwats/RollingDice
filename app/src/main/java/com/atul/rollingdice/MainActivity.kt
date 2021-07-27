package com.atul.rollingdice

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.atul.rollingdice.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var amb: ActivityMainBinding
    private var size: Int = 6
    private var luckyGame = false
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        amb = ActivityMainBinding.inflate(layoutInflater)
        setContentView(amb.root)
        setUpRoll()
    }

    private fun setUpRoll() {
        val dice = Dice()
        var count = 0
        amb.rollDice.setOnClickListener {
            if (luckyGame) {
                val luckyNumber = dice.getNumberOnRoll()
                val rolledResult = dice.getNumberOnRoll(size)
                setDiceImage(rolledResult)
                if (luckyNumber == rolledResult) {
                    Toast.makeText(
                        this,
                        "You Won!! Lucky Number was $luckyNumber",
                        Toast.LENGTH_SHORT
                    ).show()
                    count++
                }
                amb.luckyGameWinCount.text = count.toString()
            } else {
                val rolledResult = dice.getNumberOnRoll(size)
                setDiceImage(rolledResult)

            }

        }
        amb.changeDiceSize.setOnClickListener {
            startActivity(Intent(this, DifferentSides::class.java))
        }
        amb.luckyGame.setOnCheckedChangeListener { _, checkValue ->
            if (checkValue) {
                luckyGame = true
                amb.luckyGameWinCount.visibility = View.VISIBLE
                amb.luckyGameWin.visibility = View.VISIBLE

            } else {
                luckyGame = false
                count = 0
                amb.luckyGameWinCount.visibility = View.GONE
                amb.luckyGameWin.visibility = View.GONE
            }
        }
    }

    /**
     * Images for dice set on random number result
     */
    private fun setDiceImage(rolledResult: Int) {
        when (rolledResult) {
            1 -> amb.rolledNumber.setImageResource(R.drawable.dice_1)
            2 -> amb.rolledNumber.setImageResource(R.drawable.dice_2)
            3 -> amb.rolledNumber.setImageResource(R.drawable.dice_3)
            4 -> amb.rolledNumber.setImageResource(R.drawable.dice_4)
            5 -> amb.rolledNumber.setImageResource(R.drawable.dice_5)
            6 -> amb.rolledNumber.setImageResource(R.drawable.dice_6)
        }
    }

}