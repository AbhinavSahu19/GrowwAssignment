package com.example.stocksapp.db.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity("top_looser")
data class TopLooserEntity(
    @PrimaryKey(autoGenerate = false)
    var ticker: String,
    var price: String,
    var changeAmount: String,
    var changePercentage: String,
    var volume: String
)
