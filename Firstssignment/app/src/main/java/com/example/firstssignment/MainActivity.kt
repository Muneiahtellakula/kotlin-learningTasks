package com.example.firstssignment

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.ImageView
import android.widget.TextView

class MainActivity : AppCompatActivity() {
    lateinit var image1: ImageView
    lateinit var image2: ImageView

    var sc1: Int=0
    var sc2: Int =0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        image1=findViewById(R.id.imgp1)
        image2=findViewById(R.id.imgp2)

    }

    fun play_roll(view: View) {
        val r = (1..6).random()
        val tv_p1: TextView = findViewById(R.id.resultp1)//fro getting id
        sc1=sc1+r
        val tv_p1_totalScore:TextView =findViewById(R.id.player1_score)
        if (r==0)
        {
            image1.setImageResource(R.drawable.dice_1)
            tv_p1_totalScore.text = "1"
        }
        else if (r==6)
        {
            tv_p1_totalScore.text = r.toString()
        }else {
            tv_p1_totalScore.text = "Score " + sc1.toString()
            tv_p1.text = "Dice " + r.toString()
            displayDicp1(r)
        }
        /*player 2*/
        val r1 = (1..6).random()
        val tv_p2: TextView = findViewById(R.id.resultp2)
        sc2=sc2+r1
        val tv_p2_totalScore:TextView =findViewById(R.id.player2_score)
        if (r1==0)
        {
            image2.setImageResource(R.drawable.dice_1)
            tv_p2_totalScore.text = "1"
        }
        else if (r1==6)
        {
            tv_p2_totalScore.text = r1.toString()
        }else {
            tv_p2_totalScore.text = "Score " + sc2.toString()
            tv_p2.text = "Dice " + r1.toString()

        }
        displayDicp2(r1)
    }

    private fun displayDicp1(r: Int) {
        when (r) {
            1 -> image1.setImageResource(R.drawable.dice_1)
            2 -> image1.setImageResource(R.drawable.dice_2)
            3 -> image1.setImageResource(R.drawable.dice_3)
            4 -> image1.setImageResource(R.drawable.dice_4)
            5 -> image1.setImageResource(R.drawable.dice_5)
            6 -> image1.setImageResource(R.drawable.dice_6)
            else -> {
                image1.setImageResource(R.drawable.empty_dice)
            }

        }
    }
    private fun displayDicp2(r1: Int) {
        when (r1) {
            1 -> image2.setImageResource(R.drawable.dice_1)
            2 -> image2.setImageResource(R.drawable.dice_2)
            3 -> image2.setImageResource(R.drawable.dice_3)
            4 -> image2.setImageResource(R.drawable.dice_4)
            5 -> image2.setImageResource(R.drawable.dice_5)
            6 -> image2.setImageResource(R.drawable.dice_6)
            else -> {
                image2.setImageResource(R.drawable.empty_dice)
            }

        }
    }
}
