package com.example.stocksapp.utils

fun formatLargeAmount(value: String): String {
    return try {
        val num = value.toDouble()
        when {
            num >= 1_000_000_000_000 -> "${(num / 1_000_000_000_000).format(2)}T"
            num >= 1_000_000_000 -> "${(num / 1_000_000_000).format(2)}B"
            num >= 1_000_000 -> "${(num / 1_000_000).format(2)}M"
            num >= 1_000 -> "${(num / 1_000).format(2)}K"
            else -> num.format(2)
        }
    } catch (e: Exception) {
        "-"
    }
}
fun Double.format(digits: Int) = "%.${digits}f".format(this)