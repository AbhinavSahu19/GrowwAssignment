package com.example.stocksapp.api.responsedto

import com.example.stocksapp.db.entity.ActivelyTradedEntity
import com.example.stocksapp.db.entity.TopGainerEntity
import com.example.stocksapp.db.entity.TopLooserEntity
import com.google.gson.annotations.SerializedName

data class TickerItem(

    @SerializedName("ticker")
    var ticker: String,
    @SerializedName("price")
    var price: String,
    @SerializedName("change_amount")
    var changeAmount: String,
    @SerializedName("change_percentage")
    var changePercentage: String,
    @SerializedName("volume")
    var volume: String
){
    fun toTopGainer(): TopGainerEntity{
        return TopGainerEntity(
            ticker, price, changeAmount, changePercentage, volume
        )
    }

    fun toTopLooser(): TopLooserEntity{
        return TopLooserEntity(
            ticker, price, changeAmount, changePercentage, volume
        )
    }

    fun toActivelyTraded(): ActivelyTradedEntity{
        return ActivelyTradedEntity(
            ticker, price, changeAmount, changePercentage, volume
        )
    }
}