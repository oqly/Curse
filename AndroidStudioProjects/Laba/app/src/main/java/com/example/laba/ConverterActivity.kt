package com.example.laba

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast

class ConverterActivity : Activity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_converter)

        val inputDollar: EditText = findViewById(R.id.editExDollar)
        val inputEuro: EditText = findViewById(R.id.editExEuro)

        val button_send: Button = findViewById(R.id.button_send_data)
        button_send.setOnClickListener {
            if (inputDollar.text.isEmpty() or inputEuro.text.isEmpty()) {
                Toast.makeText(
                    applicationContext, "Введите все значения",
                    Toast.LENGTH_LONG).show()
            }
            else {
                val intent = Intent(this@ConverterActivity, ConvertDataActivity::class.java)
                intent.putExtra("exdollar", inputDollar.text.toString())
                intent.putExtra("exeuro", inputEuro.text.toString())
                startActivity(intent)
            }
        }
    }
}