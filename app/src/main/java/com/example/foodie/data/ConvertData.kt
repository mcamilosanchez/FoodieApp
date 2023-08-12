package com.example.foodie.data

import java.text.NumberFormat
import java.util.*

class ConvertData {

    fun stringToPrice(stringNumber: String): String {
        val priceValue = stringNumber.toDoubleOrNull() ?: 0.0
        val numberFormat = NumberFormat.getCurrencyInstance(Locale("es", "CO"))
        return numberFormat.format(priceValue)
    }
}