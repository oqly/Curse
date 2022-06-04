package com.example.testapi

import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.ListView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.Response
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley
import org.json.JSONException


class MainActivityB : AppCompatActivity() {
    private lateinit var listView: ListView
    private var requestQueue: RequestQueue? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val textView: TextView = findViewById(R.id.textView_name)
        listView = findViewById(R.id.worklist)
        val button: Button = findViewById(R.id.button_update)

        requestQueue = Volley.newRequestQueue(this)

        button.setOnClickListener {
            jsonParse()
        }

    }

    private fun jsonParse() {
        val url = "http://10.0.2.2:5000" //https://www.arbeitnow.com/api/job-board-api
        val request = JsonObjectRequest(Request.Method.GET, url, null, {
                response ->try {
            val jsonArray = response.getJSONArray("data")
            val listItems = arrayOfNulls<String>(jsonArray.length())
            for (i in 0 until jsonArray.length()) {
                val work = jsonArray.getJSONObject(i)
                val title = work.getString("title")
                val location = work.getString("location")
                listItems[i] = "$title\n\nLocation: $location"
            }

            val adapter = ArrayAdapter(
                this,
                android.R.layout.simple_list_item_1, listItems
            )
            listView.adapter = adapter
        } catch (e: JSONException) {
            e.printStackTrace()
        }
        }, { error -> error.printStackTrace() })
        requestQueue?.add(request)
    }

}