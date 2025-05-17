package com.example.stocksapp.utils

import android.content.Context
import android.content.SharedPreferences

lateinit var appContext: Context

fun getSharedPref(): SharedPreferences {
    return appContext.getSharedPreferences("MyPrefs", Context.MODE_PRIVATE)
}

val EXPLORE_SCREEN_LAST_REFRESHED = "explore_screen_last_refresh"