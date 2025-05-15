package com.example.stocksapp.responsedto

import com.google.gson.annotations.SerializedName

data class TopGainersLosersResponse(

    @SerializedName("metadata")
    var metadata: String,
    @SerializedName("last_updated")
    var lastUpdated: String,
    @SerializedName("top_gainers")
    var topGainers: List<TickerItem> = listOf(),
    @SerializedName("top_losers")
    var topLosers: List<TickerItem> = listOf(),
    @SerializedName("most_actively_traded")
    var mostActivelyTraded: List<TickerItem> = listOf()

)