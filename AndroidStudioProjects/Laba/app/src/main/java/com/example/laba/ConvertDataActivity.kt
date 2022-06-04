package com.example.laba

import android.annotation.SuppressLint
import android.app.Activity
import android.os.Bundle
import android.widget.*

class ConvertDataActivity : Activity(), ConvertInterface {
    @SuppressLint("ResourceAsColor", "SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_convert_data)

        val ex_dollar = intent.extras?.getString("exdollar")
        val ex_euro = intent.extras?.getString("exeuro")

        val textView_dollar: TextView = findViewById(R.id.textView_dollar)
        val textView_euro: TextView = findViewById(R.id.textView_euro)

        textView_dollar.text = "Курс доллара: $ex_dollar"
        textView_euro.text = "Курс евро: $ex_euro"

        if (ex_dollar.toString().toFloat() > 100.0){
            textView_dollar.setTextColor(resources.getColor(R.color.red, null))
        }
        if (ex_euro.toString().toFloat() > 150.0){
            textView_euro.setTextColor(resources.getColor(R.color.red, null))
        }

        val dollarRadioButton: RadioButton = findViewById(R.id.radioButton_dollar)
        val inputSum: EditText = findViewById(R.id.editSum)
        val outputRes: TextView = findViewById(R.id.textView_result)

        val button_convert: Button = findViewById(R.id.button_convert)
        button_convert.setOnClickListener {
            if (inputSum.text.isEmpty()) {
                Toast.makeText(
                    applicationContext, "Введите количество",
                    Toast.LENGTH_LONG).show()
            }
            else {
                val inputValue = inputSum.text.toString().toFloat()
                if (dollarRadioButton.isChecked) {
                    outputRes.text = convertDollar(inputValue, ex_dollar.toString().toFloat()).toString() + " ₽"
                } else {
                    outputRes.text = convertEuro(inputValue, ex_euro.toString().toFloat()).toString() + " ₽"
                }
            }
        }

    }

    /*
    // Конвертируем в долларах
    private fun convertDollar(dollar: Float, exdollar: Float): Float = (dollar * exdollar)

    // Конвертируем в евро
    private fun convertEuro(euro: Float, exeuro: Float): Float = (euro * exeuro)
     */
}