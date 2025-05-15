package com.example.stocksapp.responsedto

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

)