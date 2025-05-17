package com.example.stocksapp

import android.app.Application
import com.example.stocksapp.utils.appContext
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class StocksApplication : Application(){
    override fun onCreate() {
        super.onCreate()
        appContext = this
    }
}