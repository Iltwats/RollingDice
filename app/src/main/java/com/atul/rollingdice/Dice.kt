package com.atul.rollingdice

class Dice {
    fun getNumberOnRoll(sides: Int = 6): Int {
        val diceNumber = 1..sides
        return diceNumber.random()
    }
}