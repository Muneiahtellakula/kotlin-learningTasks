package com.example.task_ex

import android.content.Context
import android.os.AsyncTask
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ProgressBar
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import org.json.JSONArray
import org.json.JSONObject
import java.io.BufferedReader
import java.io.InputStream
import java.net.HttpURLConnection
import java.net.URL
import javax.net.ssl.HttpsURLConnection

class MainActivity : AppCompatActivity() {

    lateinit var result: TextView
    val LINK: String =
        "https://raw.githubusercontent.com/LearnWebCode/json-example/master/animals-1.json"
    lateinit var progressBar: ProgressBar
    lateinit var recyclerView: RecyclerView
    lateinit var list: MutableList<FactDetails>
    lateinit var myViewModel: MyViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        list = mutableListOf()
        //result = findViewById(R.id.result)
        recyclerView = findViewById(R.id.recycler)
        val btn = findViewById<Button>(R.id.fetch_btn)
        progressBar = findViewById(R.id.progress_bar)
        myViewModel = ViewModelProvider(this).get(MyViewModel::class.java)
        progressBar.visibility = View.GONE

        btn.setOnClickListener {
            progressBar.visibility = View.VISIBLE
           // myViewModel = ViewModelProvider(this).get(MyViewModel::class.java)
            CoroutineScope(Dispatchers.IO).launch {
                fetchCatFacts()
            }


        }


    }

    suspend fun fetchCatFacts() {
        val url = URL(LINK)
        val httpsURLConnection: HttpsURLConnection = url.openConnection() as HttpsURLConnection
        val inputStream: InputStream = httpsURLConnection.inputStream
        val text = inputStream.bufferedReader().use(BufferedReader::readText)
        withContext(Dispatchers.Main) {
            setValueOnResult(text)
        }

    }

    private fun setValueOnResult(text: String) {
        progressBar.visibility = View.GONE
        val rootAry: JSONArray = JSONArray(text)
        for (i in 0..rootAry.length()-1){
            val rootObj = rootAry.getJSONObject(i)
            val name: String = rootObj.getString("name")
            val species: String = rootObj.getString("species")
            list.add(FactDetails(name,species))
           // Toast.makeText(this,"data "+list.size,Toast.LENGTH_SHORT).show()
        }
        Toast.makeText(this,"data "+list.size,Toast.LENGTH_SHORT).show()
        myViewModel.list.observe(this, Observer {
                li -> val adapter = MyAdapter(this,li)
            recyclerView.adapter = adapter
        })
        recyclerView.layoutManager=LinearLayoutManager(this)

    }

    class MyAdapter(val context: Context, val list: MutableList<FactDetails>) :
        RecyclerView.Adapter<RecyclerViewHolder>() {
        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerViewHolder {
            return RecyclerViewHolder(
                LayoutInflater.from(context).inflate(
                    R.layout.design,
                    parent,
                    false
                )
            )
        }

        override fun getItemCount(): Int {
            return list.size
        }

        override fun onBindViewHolder(holder: RecyclerViewHolder, position: Int) {
            holder.t.text = (list.get(position).name)
            holder.t1.text = (list.get(position).spice)
        }


    }

    class RecyclerViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val t = itemView.findViewById<TextView>(R.id.one)
        val t1 = itemView.findViewById<TextView>(R.id.two)

    }
}