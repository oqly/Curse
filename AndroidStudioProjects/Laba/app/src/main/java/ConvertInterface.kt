package com.example.laba

interface ConvertInterface {
    fun convertDollar(dollar: Float, exdollar: Float): Float = (dollar * exdollar)

    fun convertEuro(euro: Float, exeuro: Float): Float = (euro * exeuro)
}