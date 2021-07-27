package com.atul.rollingdice

import android.os.Bundle
import android.view.LayoutInflater
import androidx.appcompat.app.AppCompatActivity
import com.atul.rollingdice.databinding.ActivityDifferentSidesBinding
import com.atul.rollingdice.databinding.DiceSizeDialogBinding
import com.google.android.material.dialog.MaterialAlertDialogBuilder

class DifferentSides : AppCompatActivity() {
    private lateinit var ads: ActivityDifferentSidesBinding
    private var size: Int = 6
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        ads = ActivityDifferentSidesBinding.inflate(layoutInflater)
        setContentView(ads.root)
        setUpRoller()
    }

    private fun setUpRoller() {
        val dice = Dice()
        ads.rollDice.setOnClickListener {
            val rolledResult = dice.getNumberOnRoll(size)
            ads.rolledNumber.text = rolledResult.toString()
        }
        ads.changeDiceSize.setOnClickListener {
            inflateDialogForDiceSize()
        }

    }

    private fun inflateDialogForDiceSize() {
        val binding = DiceSizeDialogBinding.inflate(LayoutInflater.from(this))
        val builder = MaterialAlertDialogBuilder(this)
        builder.setView(binding.root)
            .setTitle("Set your new Dice Size")
            .setMessage("Current Default is Six")
            .setCancelable(false)
            .setPositiveButton("CHANGE") { _, _ ->
                val value = binding.diceSize.text.toString()
                size = value.toInt()
            }
            .setNegativeButton("CANCEL") { dialog, _ ->
                dialog.dismiss()
            }
            .create()
            .show()
    }

    override fun onBackPressed() {
        finish()
        super.onBackPressed()
    }
}