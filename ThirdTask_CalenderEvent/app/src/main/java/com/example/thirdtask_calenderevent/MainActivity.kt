package com.example.thirdtask_calenderevent

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.databinding.DataBindingUtil
import com.example.thirdtask_calenderevent.databinding.ActivityMainBinding
import kotlinx.android.synthetic.main.activity_main.view.*
import java.util.*

class MainActivity : AppCompatActivity() {
lateinit var databinding:ActivityMainBinding
    var cal = Calendar.getInstance()
    var date: Button? = null
    var textview_date: TextView? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        databinding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        databinding.button.setOnClickListener { openCalender() }

    }

    private fun openCalender() {
        //TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
        val c : Calendar = Calendar.getInstance()
        val intent = Intent(Intent.ACTION_EDIT)
        intent.setType("vnd.android.cursor.item/event")
        intent.putExtra("startTime",c.timeInMillis)
        intent.putExtra("endTime",c.timeInMillis+60+60+1000)
        intent.putExtra("title",databinding.textView.text.toString())
        startActivity(intent)
    }
}
