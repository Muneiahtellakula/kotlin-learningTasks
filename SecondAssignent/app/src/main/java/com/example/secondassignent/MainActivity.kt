package com.example.secondassignent

import android.database.DatabaseUtils
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import androidx.databinding.DataBindingUtil
import com.example.secondassignent.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
lateinit var databining:ActivityMainBinding
    var sc1: Int=0
    var sc2: Int =0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
       databining=DataBindingUtil.setContentView(this,R.layout.activity_main)
        databining.roller.setOnClickListener { rollthedice() }
    }

    private fun rollthedice() {
        //TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
        val r = (1..6).random()
       /* val tv_p1: TextView = findViewById(R.id.resultp1)//fro getting id
        sc1=sc1+r
        val tv_p1_totalScore: TextView =findViewById(R.id.player1_score)*/
        if (r==0)
        {
            databining.imgp1.setImageResource(R.drawable.dice_1)
            databining.resultp1.text = "1"
        }
        else if (r==6)
        {
            databining.resultp1.text = r.toString()
        }else {
            databining.player1Score.text = "Score " + sc1.toString()
            databining.resultp1.text = "Dice " + r.toString()
            displayDicp1(r)
        }
        /*player 2*/
        val r1 = (1..6).random()
        if (r1==0)
        {
            databining.imgp2.setImageResource(R.drawable.dice_1)
            databining.player2Score.text = "1"
        }
        else if (r1==6)
        {
            databining.resultp2.text = r1.toString()
        }else {
            databining.player2Score.text = "Score " + sc2.toString()
            databining.resultp2.text = "Dice " + r1.toString()

        }
        displayDicp2(r1)
    }

    private fun displayDicp1(r: Int) {
        when (r) {
            1 -> databining.imgp1.setImageResource(R.drawable.dice_1)
            2 -> databining.imgp1.setImageResource(R.drawable.dice_2)
            3 -> databining.imgp1.setImageResource(R.drawable.dice_3)
            4 -> databining.imgp1.setImageResource(R.drawable.dice_4)
            5 -> databining.imgp1.setImageResource(R.drawable.dice_5)
            6 -> databining.imgp1.setImageResource(R.drawable.dice_6)
            else -> {
                databining.imgp1.setImageResource(R.drawable.empty_dice)
            }

        }
    }
    private fun displayDicp2(r1: Int) {
        when (r1) {
            1 -> databining.imgp2.setImageResource(R.drawable.dice_1)
            2 -> databining.imgp2.setImageResource(R.drawable.dice_2)
            3 -> databining.imgp2.setImageResource(R.drawable.dice_3)
            4 -> databining.imgp2.setImageResource(R.drawable.dice_4)
            5 -> databining.imgp2.setImageResource(R.drawable.dice_5)
            6 -> databining.imgp2.setImageResource(R.drawable.dice_6)
            else -> {
                databining.imgp2.setImageResource(R.drawable.empty_dice)
            }

        }
    }
}
