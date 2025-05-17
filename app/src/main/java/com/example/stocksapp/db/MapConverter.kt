package com.example.stocksapp.db

import androidx.room.TypeConverter
import com.example.stocksapp.api.responsedto.graph.StockGraphDataItem
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

object MapConverter {

    private val gson = Gson()
    private val type = object : TypeToken<Map<String, StockGraphDataItem>>() {}.type

    @TypeConverter
    fun fromMap(map: Map<String, StockGraphDataItem>?): String {
        return gson.toJson(map, type)
    }

    @TypeConverter
    fun toMap(json: String?): Map<String, StockGraphDataItem> {
        return gson.fromJson(json, type)
    }
}
