package com.example.curse

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.ArrayAdapter
import android.widget.ListView
import android.widget.ProgressBar
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.android.volley.DefaultRetryPolicy
import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley
import org.json.JSONException


class ListActivity : AppCompatActivity() {
    lateinit var listView: ListView
    lateinit var progressBar: ProgressBar
    lateinit var textView: TextView
    var requestQueue: RequestQueue? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_list)

        val id = intent.extras?.getString("id")

        progressBar = findViewById(R.id.progressBar)
        progressBar.visibility = ProgressBar.VISIBLE
        textView = findViewById(R.id.name_textView)
        textView.text = getString(R.string.loading)

        listView = findViewById(R.id.facultyList)
        requestQueue = Volley.newRequestQueue(this)

        val service = Parser(id, this)
        service.parse()
    }
}