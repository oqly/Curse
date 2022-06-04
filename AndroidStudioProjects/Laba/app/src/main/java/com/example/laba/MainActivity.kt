package com.example.laba

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView

class MainActivity : AppCompatActivity() {
    private var counter: Int = 0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val textView: TextView = findViewById(R.id.textView)
        val button_add: Button = findViewById(R.id.button_add)
        val button_sub: Button = findViewById(R.id.button_sub)
        val button_about: Button = findViewById(R.id.button_about)
        val button_converter: Button = findViewById(R.id.button_converter)

        button_add.setOnClickListener {
            textView.text = "${++counter}"
        }

        button_sub.setOnClickListener {
            textView.text = "${--counter}"
        }

        button_about.setOnClickListener {
            val intent = Intent(this@MainActivity, AboutActivity::class.java)
            startActivity(intent)
        }

        button_converter.setOnClickListener {
            val intent = Intent(this@MainActivity, ConverterActivity::class.java)
            startActivity(intent)
        }
    }
}