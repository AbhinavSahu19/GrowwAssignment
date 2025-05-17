package com.example.stocksapp.utils

fun normalizeCapitalStrings(input: String): String {
    return input.lowercase()
        .split(" ")
        .joinToString(" ") { word ->
            word.replaceFirstChar { if (it.isLowerCase()) it.titlecase() else it.toString() }
        }
}