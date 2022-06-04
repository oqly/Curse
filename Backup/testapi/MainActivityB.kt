package com.example.testapi

import android.os.Bundle
import android.widget.Button
import android.widget.ListView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.android.volley.RequestQueue
import com.android.volley.toolbox.Volley


class MainActivity : AppCompatActivity(), Service() {
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
            requestQueue = jsonParse(requestQueue!!, listView)
        }

    }

}