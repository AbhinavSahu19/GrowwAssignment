package com.example.stocksapp.db.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName


@Entity("search_keyword")
data class SearchKeywordEntity(
    @PrimaryKey(autoGenerate = false)
    var symbol: String,
    var name: String,
    var type: String,
    var region: String,
    var marketOpen: String,
    var marketClose: String,
    var timezone: String,
    var currency: String,
    var matchScore: String,

    var storedAt: Long = System.currentTimeMillis()
)
