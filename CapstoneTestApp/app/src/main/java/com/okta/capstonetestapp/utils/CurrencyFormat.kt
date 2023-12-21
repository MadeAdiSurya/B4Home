package com.okta.capstonetestapp.utils

import java.text.NumberFormat
import java.util.Locale

fun currencyFormat(number: String?): String? {
    val formatRupiah = NumberFormat.getCurrencyInstance(Locale("in", "ID"))
    return number?.let { formatRupiah.format(it.toDouble()) }
}